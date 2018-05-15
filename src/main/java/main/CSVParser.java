package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.RingChartData;

public class CSVParser {

	public static List<RingChartData> parseXLS(){
		List<RingChartData> dataList = readCSV("dataFiles/Ring Chart Data.csv");
		return dataList;
	}
	
	public static List<RingChartData> parseXLS(String filePath){
		List<RingChartData> dataList = readCSV(filePath);
		return dataList;
	}
	
	private static List<RingChartData> readCSV(String filePath) {
		List<RingChartData> dataList = new ArrayList<>();
		List<String> stringList = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))){
			
			stringList = stream.skip(1).collect(Collectors.toList());
			
			for(String line : stringList) {
				dataList.add(processLine(line));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	private static RingChartData processLine(String line) {
		String[] array = line.split(",");
		Double weight = 0.0;
		try
		{
			weight = Double.parseDouble(array[2]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Parse Error: Cant convert to Double.");
			return null;
		}
		return new RingChartData(array[1], weight);
	}
}
