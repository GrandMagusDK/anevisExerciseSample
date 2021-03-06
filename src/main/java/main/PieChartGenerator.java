package main;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import model.PieChartData;

public class PieChartGenerator extends ApplicationFrame{

	private static final long serialVersionUID = -2171235022443465432L;
	
	public PieChartGenerator(String title, List<PieChartData> dataList) {
		super(title);
        PieDataset dataset = createDataset(dataList);
        JFreeChart chart = createChart(dataset);
        
        JPanel rootPanel = new JPanel();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        rootPanel.add(chartPanel);
        rootPanel.add(createLegendPanel((PiePlot) chart.getPlot()));
        
        setContentPane(chartPanel);
        createJPEG(chart);
	}

	private static JPanel createLegendPanel(PiePlot plot) {
	    JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
	    Iterator<?> iterator = plot.getLegendItems().iterator();
	    while (iterator.hasNext()) {
	        LegendItem item = (LegendItem) iterator.next();
	        JLabel label = new JLabel(item.getLabel());
	        label.setText(item.getDescription());
	        panel.add(label);
	    }
	    return panel;
	}
	
	private PieDataset createDataset(List<PieChartData> dataList) {
		final DefaultPieDataset dataset = new DefaultPieDataset();
        
		for(PieChartData entry : dataList) {
			dataset.setValue(entry.getCountry(), entry.getWeight());
		}
        return dataset;        
    }
	
	private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart",  // chart title
            dataset,             // data
            false,               // include legend
            true,
            false
        );

        return chart;
    }
	
	private void createJPEG(JFreeChart chart) {
		int width = 640;   
		int height = 480; 
	    File pieChart = new File("generatedImages/PieChart.jpeg" ); 
	    try 
	    {
			ChartUtils.saveChartAsJPEG( pieChart , chart , width , height );
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}
}
