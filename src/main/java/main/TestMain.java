package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.PieChartDataDAO;
import dao.RingChartDataDAO;
import model.PieChartData;
import model.RingChartData;

public class TestMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		PieChartDataDAO pcdDAO = context.getBean(PieChartDataDAO.class);
		RingChartDataDAO rcdDAO = context.getBean(RingChartDataDAO.class);
		
		cleanupPrevious(pcdDAO, rcdDAO);
		
		List<PieChartData> PCDSaveList = XLSParser.parseXLS();
		List<RingChartData> RCDSaveList = CSVParser.parseXLS();
		
		pcdDAO.saveList(PCDSaveList);
		rcdDAO.saveList(RCDSaveList);
		
		List<PieChartData> PCDList = pcdDAO.list();
		List<RingChartData> RCDList = rcdDAO.list();
		
		PieChartGenerator PieChartGen = new PieChartGenerator("Pie Chart", PCDList);
		RingChartGenerator RingChartGen = new RingChartGenerator("Pie Chart", RCDList);
		
		context.close();	
	}

	private static void cleanupPrevious(PieChartDataDAO pcdDAO, RingChartDataDAO rcdDAO) {
		//cleanup DB
		pcdDAO.clearTable();
		rcdDAO.clearTable();
		
		//cleanup Images
		File folder = new File("generatedImages/");
		List<File> listOfFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));
		for(File file : listOfFiles) 
		{
			file.delete();
		}
 	}
}
