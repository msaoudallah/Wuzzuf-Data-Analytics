package frontend;

import static tech.tablesaw.aggregate.AggregateFunctions.count;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.knowm.xchart.*;

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
		Scanner sc = new Scanner(System.in);
		Scanner sc0 = new Scanner(System.in);
		String user_input = "y";
		while(user_input.equals("y"))
		{
			System.out.println("Please choose one of the following options:\n" +
					"1. Display the Wuzzuf data summary\n" +
					"2. Display the head of the Wuzzuf dataset\n" +
					"3. Display the shape of the Wuzzuf dataset after removing nulls and duplicates\n" +
					"4. Display the total number of jobs posted by each company\n" +
					"5. Display a pie chart for the top 10 companies with highest number of jobs postings\n" +
					"6. Display a bar chart for the top 10 job postings with highest numbers\n" +
					"7. Display a bar chart for the top 10 areas with highest number job postings\n" +
					"8. Display the count of each skill mentioned\n" +
					"Waiting for user input...");
			x = sc.nextInt();
			switch (x)
			{
				case 1: //data summary
					System.out.print(consumer.summary());
					break;
				case 2: //displaying the head of our dataset
					System.out.println(consumer.head());
					break;
				case 3: //displaying shape of data after removing nulls and duplicates
					System.out.println(consumer.clean());
					break;
				case 4: //displaying the total number of jobs each company has posted
					System.out.println(consumer.jobsPerCompany());
					break;
				case 5: //displaying a pie chart for the top 10 companies job
					HashMap<String,Double> hm= consumer.jobsPerCompanyPie();
					List<Double> Count= new ArrayList<Double>();
					List<String> Companies =  new ArrayList<String>();
					hm.entrySet().forEach(entry -> {
						System.out.println(entry.getKey() + ":" + entry.getValue());
						Count.add(entry.getValue());
						Companies.add(entry.getKey());
					});

					PieChart chart = new PieChartBuilder().width(800).height(600).title("Pie Chart with 4 Slices").build();
					new SwingWrapper(chart).displayChart();

					for (int i =0; i<Count.size(); i++) {

						chart.addSeries(Companies.get(i),Count.get(i));
					}
					break;
				case 6:
					HashMap<String,Integer> jobs_receiver = consumer.mostPopularJobTitles();
					CategoryChart most_popular_jobs_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular job titles").xAxisTitle("Job").yAxisTitle("Number of jobs").build();
					ArrayList<String> job_titles = new ArrayList<String>(jobs_receiver.keySet());
					ArrayList<Integer> job_count = new ArrayList<Integer>(jobs_receiver.values());
					most_popular_jobs_bar_chart.addSeries("Jobs",job_titles,job_count);
					most_popular_jobs_bar_chart.getStyler().setXAxisLabelRotation(45);
					new SwingWrapper(most_popular_jobs_bar_chart).displayChart();
					break;
				case 7:
					HashMap<String,Integer> areas_receiver = consumer.mostPopularAreas();
					CategoryChart most_popular_areas_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular areas").xAxisTitle("Area").yAxisTitle("Number of jobs").build();
					ArrayList<String> areas = new ArrayList<String>(areas_receiver.keySet());
					ArrayList<Integer> areas_count = new ArrayList<Integer>(areas_receiver.values());
					most_popular_areas_bar_chart.addSeries("Jobs",areas,areas_count);
					new SwingWrapper(most_popular_areas_bar_chart).displayChart();
					break;
				case 8: //printing the count of each skill mentioned
					System.out.println(consumer.skillsCount());
					break;
				default:
					break;
			}
			System.out.println("\nDo you want to do more operations (y/n)?");
			user_input = sc.next();
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
