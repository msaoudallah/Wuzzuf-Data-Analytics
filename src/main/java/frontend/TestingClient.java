package frontend;

import static tech.tablesaw.aggregate.AggregateFunctions.count;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;

import dao.Wuzzuf;
import joinery.DataFrame;
import smile.clustering.KMeans;
import smile.clustering.PartitionClustering;
import smile.data.*;
import smile.plot.swing.Canvas;
import smile.plot.swing.Plot;
import smile.plot.swing.ScatterPlot;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;

import javax.swing.*;

public class TestingClient {
	static Consumer consumer = new Consumer();
	public static void main(String[] args) throws IOException, InvocationTargetException, InterruptedException {
		int x=0;
		Scanner sc = new Scanner(System.in);
		Scanner sc0 = new Scanner(System.in);
		String user_input = "y";
		while(user_input.equals("y"))
		{
			System.out.println("Please choose one of the following options:\n" +
					"1.  Display the Wuzzuf data summary\n" +
					"2.  Display the head of the Wuzzuf dataset\n" +
					"3.  Display the shape of the Wuzzuf dataset after removing nulls and duplicates\n" +
					"4.  Display the total number of jobs posted by each company\n" +
					"5.  Display a pie chart for the top 10 companies with highest number of jobs postings\n" +
					"6.  Display a bar chart for the top 10 job postings with highest numbers\n" +
					"7.  Display a bar chart for the top 10 areas with highest number job postings\n" +
					"8.  Display the count of each skill mentioned\n" +
					"9.  Display sample from data after factorizing YearsExp\n" +
					"10. Display Result of K-means clustering algorithm \n" +
					
					
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
					LinkedHashMap<String,Double> hm= consumer.jobsPerCompanyPie();
					List<Double> Count= new ArrayList<Double>();
					List<String> Companies =  new ArrayList<String>();
					hm.entrySet().forEach(entry -> {
						System.out.println(entry.getKey() + ":" + entry.getValue());
						Count.add(entry.getValue());
						Companies.add(entry.getKey());
					});

					PieChart chart = new PieChartBuilder().width(800).height(600).title("Most Demanding Companies for jobs").build();
					JFrame jobs_frame = new SwingWrapper(chart).displayChart();
					javax.swing.SwingUtilities.invokeLater(
							()->jobs_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
					);
					for (int i =0; i<Count.size(); i++) {

						chart.addSeries(Companies.get(i),Count.get(i));
					}
					jobs_frame.toFront();
					jobs_frame.requestFocus();
					break;
				case 6:
					LinkedHashMap<String,Integer> jobs_receiver = consumer.mostPopularJobTitles();
					CategoryChart most_popular_jobs_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular job titles").xAxisTitle("Job").yAxisTitle("Number of jobs").build();
					ArrayList<String> job_titles = new ArrayList<String>(jobs_receiver.keySet());
					ArrayList<Integer> job_count = new ArrayList<Integer>(jobs_receiver.values());
					most_popular_jobs_bar_chart.addSeries("Jobs",job_titles,job_count);
					most_popular_jobs_bar_chart.getStyler().setXAxisLabelRotation(45);
					JFrame jobs_frame_0 = new SwingWrapper(most_popular_jobs_bar_chart).displayChart();
					javax.swing.SwingUtilities.invokeLater(
							()->jobs_frame_0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
					);
					jobs_frame_0.toFront();
					jobs_frame_0.requestFocus();
					break;
				case 7:
					LinkedHashMap<String,Integer> areas_receiver = consumer.mostPopularAreas();
					CategoryChart most_popular_areas_bar_chart = new CategoryChartBuilder().width(1500).height(600).title("Most popular areas").xAxisTitle("Area").yAxisTitle("Number of jobs").build();
					ArrayList<String> areas = new ArrayList<String>(areas_receiver.keySet());
					ArrayList<Integer> areas_count = new ArrayList<Integer>(areas_receiver.values());
					most_popular_areas_bar_chart.addSeries("Jobs",areas,areas_count);
					JFrame areas_frame = new SwingWrapper(most_popular_areas_bar_chart).displayChart();
					javax.swing.SwingUtilities.invokeLater(
							()->areas_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
					);
					areas_frame.toFront();
					areas_frame.requestFocus();
					break;
				case 8: //printing the count of each skill mentioned
					HashMap<String,Integer> skillsCount = consumer.skillsCount();
					skillsCount
			            = skillsCount.entrySet()
			                  .stream()
			                  .sorted((i2, i1)
			                              -> i1.getValue().compareTo(
			                                  i2.getValue()))
			                  .limit(15)
			                  .collect(Collectors.toMap(
			                      Map.Entry::getKey,
			                      Map.Entry::getValue,
			                      (e1, e2) -> e1, LinkedHashMap::new));
			 
					skillsCount.entrySet().forEach(entry -> {
						System.out.println(entry.getKey() + " : " + entry.getValue());

					});
					break;
				case 9: // years of exp factorization
					String res = consumer.yearsExpFactorization();
					System.out.println(res);
					break;
				case 10: // kmean clustering
					double[][] kmeansData = consumer.kmeans();
					KMeans km2 = PartitionClustering.run(10, () -> KMeans.fit(kmeansData, 10));
					JFrame tmp_frame = ScatterPlot.of(kmeansData, km2.y, '.').canvas().setAxisLabel(0, "K-means Clustering").window();
					tmp_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					tmp_frame.toFront();
					tmp_frame.requestFocus();
					break;
				default:
					break;
			}
			System.out.println("\nDo you want to do more operations (y/n)?");
			user_input = sc.next();
		}
		
		
		
	}

 
	
}
