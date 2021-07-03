package frontend;

import static tech.tablesaw.aggregate.AggregateFunctions.count;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import dao.Wuzzuf;
import joinery.DataFrame;

import smile.data.*;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

public class TestingClient {
	static Consumer consumer = new Consumer();
	public static void main(String[] args) throws IOException {
	
		int x=0;
		
		switch (x) {
		case 1: //data summary
			System.out.print(consumer.summary());
			break;
		case 2: //displaying the head of our dataset
			System.out.println(consumer.head());
			break;
		case 4: //displaying shape of data after removing nulls and duplicates
			System.out.println(consumer.clean());
			break;
		case 5: //displaying the total number of jobs each company has posted
			System.out.println(consumer.jobsPerCompany());
			break;
		case 6: //displaying a pie chart for the top 10 companies job
 			HashMap<String,Double> hm= consumer.jobsPerCompanyPie();
			List<Double> Count= new ArrayList<Double>();  
			List<String> Companies =  new ArrayList<String>();  
			hm.entrySet().forEach(entry -> {
			    System.out.println(entry.getKey() + ":" + entry.getValue());
			    Count.add(entry.getValue());
			    Companies.add(entry.getKey());
			});
			
			PieChart chart =
				    new PieChartBuilder().width(800).height(600).title("Pie Chart with 4 Slices").build();
			
			for (int i =0; i<Count.size(); i++) {
				
				chart.addSeries(Companies.get(i),Count.get(i));
			}
			break;
		case 10: //printing the count of each skill mentioned
			System.out.println(consumer.skillsCount());
			break;
		default:
			break;
		}
		
//		String summary = consumer.summary();			
//		
//		String head= consumer.head();
//		
		
		
		
//		HashMap<String,Integer> skillCount = consumer.skillsCount();
//		skillCount
//         = skillCount.entrySet()
//               .stream()
//               .sorted((i1, i2)
//                           -> i2.getValue().compareTo(
//                               i1.getValue()))
//               .limit(15)
//               .collect(Collectors.toMap(
//                   Map.Entry::getKey,
//                   Map.Entry::getValue,
//                   (e1, e2) -> e1, LinkedHashMap::new));
//		
//		
//		skillCount.entrySet().forEach(entry -> {
//		    System.out.println(entry.getKey() + ":" + entry.getValue());
//		});
//		
		

//		String summary = consumer.summary();
//		System.out.print(summary);
//		String head= consumer.head();
//		System.out.println(head);

//		String clean= consumer.clean();
//		System.out.println(clean);


//		String jobsPerCompany= consumer.jobsPerCompany();
//		System.out.println(jobsPerCompany);


		
		
		
		new SwingWrapper(chart).displayChart();

		
		
		
		

//		int[] factorizzedYears = consumer.yearsExpFactorization();
//		for (int i : factorizzedYears) {
//			System.out.println(i);
//		}
//		

		
		
		
		
		
		
		
	}

	
	public static Table cleaned_df() throws IOException
	{
		Table df= Table.read().csv("Wuzzuf_Jobs.csv");
		Table temp = df.dropDuplicateRows();
		Table result= temp.dropRowsWithMissingValues();
		return result;
	}
	
 
	
}
