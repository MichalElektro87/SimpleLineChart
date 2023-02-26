package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class LineChartSample extends Application {
 
    @Override public void start(Stage stage) {
    	stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
    	
    	try (FileWriter fin = new FileWriter("data.txt")) {
			
			for (int i = 0; i < 100; i += 2) {
				fin.write(i + " " + (i+2) + "\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    		ArrayList<Integer> seriesX = new ArrayList<>();
    		ArrayList<Integer> seriesY = new ArrayList<>();
    		
    		try (Scanner scanner = new Scanner(new FileReader("data.txt"))) {
				int i = 1;
				while (scanner.hasNextInt()) {
					
					if (i % 2 != 0) {
						seriesX.add(scanner.nextInt());
					}
					else {
						seriesY.add(scanner.nextInt());
					}
					i++;
				}
				
				for (i = 0; i < seriesX.size(); i++) {
					series.getData().add(new XYChart.Data(seriesX.get(i), seriesY.get(i)));
				}
			
    		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    
    	
    	
    	
        
    
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}