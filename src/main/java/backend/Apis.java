package backend;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
import org.apache.commons.csv.CSVFormat;
=======
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
>>>>>>> branch 'master' of https://msaoudallah@github.com/msaoudallah/repo.git
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static tech.tablesaw.aggregate.AggregateFunctions.*;

import tech.tablesaw.api.*;
import tech.tablesaw.plotly.api.*;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Page;
import tech.tablesaw.plotly.display.Browser;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;


import dao.Wuzzuf;
import dao.WuzzufDAOImp;

import joinery.DataFrame;
import smile.data.measure.NominalScale;
import smile.data.vector.BaseVector;
import smile.data.vector.DoubleVector;
import smile.data.vector.IntVector;
import smile.io.CSV;
import smile.io.Read;
import smile.io.Write;
import smile.neighbor.lsh.Hash;
//import tech.tablesaw.*;
//import smile.plot.swing.Plot;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;


@RestController
public class Apis {

	
	//1. Read data set and convert it to dataframe or Spark RDD and display some from it.
	// TODO
	// read the csv file, and display head rows
	// change signature if needed
	@GetMapping("/wuzzuf/head")
	public String head() throws IOException
	{
		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		Table head= df.first(5);

		return head.toString();
	}	
	
	
	
	//2. Display structure and summary of the data.
		@GetMapping("/wuzzuf/summary")
	public String summary() throws IOException, URISyntaxException {

		String res ="";

		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		
		res += df.summary().toString();
		res += "\n";
		res += df.shape();
		return res;
	}
		
	

	//3. Clean the data (null, duplications) @Othman
	
	// TODO
	// read the csv file, clean it
	// reomove null and duplicate rows
	// change signature if needed
	// save the new file into csv such that we use it in next steps
	@GetMapping("/wuzzuf/clean")
	public String clean() throws  IOException
	{
		Table df= Table.read().csv("Wuzzuf_Jobs.csv");
		Table temp = df.dropDuplicateRows();
		Table result= temp.dropRowsWithMissingValues();

		return "Shape before removing duplicates: "+ df.shape()+"\nAfter: "+temp.shape()+
				"\nShape before removing nulls: "+ temp.shape()+ "\nAfter: "+ result.shape();
	}	

	// this function is used to return data after duplicates, and nulls have been removed
	public Table cleaned_df() throws IOException
	{
		Table df= Table.read().csv("Wuzzuf_Jobs.csv");
		Table temp = df.dropDuplicateRows();
		Table result= temp.dropRowsWithMissingValues();
		return result;
	}
	
	//4. Count the jobs for each company and display that in order (What are the most demanding companies for jobs?) @Othman
	//5. Show step 4 in a pie chart 	@Othman
	
	// TODO
	// read the new csv file
	// do step 4
	// check how to return a pie chart (png file) ? 
	// change signature if needed
	@GetMapping("/wuzzuf/jobs/companies")
	public String jobsPerCompany() throws IOException
	{
		Table df= cleaned_df();
		Table jobsPerCompany= df.retainColumns("Company", "Title");
		Table summary= jobsPerCompany.summarize("Title",  count).by("Company");
		Table summary1= summary.sortDescendingOn("Count [Title]");
//		String cout_summ= summary1.summarize("Count [Title]", sum).apply().toString();
		return summary1.toString();//+ "\n "+ cout_summ;

	}

	@GetMapping("/wuzzuf/jobs/pieChart")
	public String jobsPerCompanyPie() throws IOException
	{
		Table df= cleaned_df();
		Table jobsPerCompany= df.retainColumns("Company", "Title");
		Table summary= jobsPerCompany.summarize("Title",  count).by("Company");
		Table summary1= summary.sortDescendingOn("Count [Title]");

		Table tablePieCharted= summary1.first(10);
		Figure f = PiePlot.create("Jobs/Company", tablePieCharted, "Company", "Count [Title]");
		Plot.show(f);

//		Plot.show(new Figure(layout, trace));
		return tablePieCharted.toString();
	}
	
	//6. Find out What are it the most popular job titles?  @Samy
	//7. Show step 6 in bar chart @Samy
	// TODO
	// read the new csv file
	// do step 6
	// check how to return a pie chart (png file) ? 
	// change signature if needed
	@GetMapping("/wuzzuf/jobs/count")
	public String jobsCount() {

		String res ="test";

		return res;
	}	
	
	
	//8. Find out the most popular areas?   @Samy
	//9. Show step 8 in bar chart       @Samy


	// TODO
	// read the new csv file
	// do step 8
	// check how to return a bar chart (png file) ? 
	// change signature if needed
	@GetMapping("/wuzzuf/areas/count")
	public String areasCount() {

		String res ="test";

		return res;
	}	
	
	
	//10. Print skills one by one and how many each repeated and order the output to  @SAIDDDDDDD
	//find out the most important skills required?
	
	
	// TODO
	// read the new csv file
	// read the skills column
	// split by ','
	// count repitions
	// print top 10 skills with count for each
	// change signature if needed
	@GetMapping("/wuzzuf/skills/count")
	public HashMap<String,Integer> skillsCount() throws IOException {

		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		String res = "";
		Column<String> skills = (Column<String>) df.column("Skills");
		
		HashMap<String,Integer> skillsMap = new HashMap<String,Integer> ();
		for (String s : skills) {
			String[] jobskills = s.split(", ");
			for (String skill: jobskills) {
				if (skillsMap.containsKey(skill)) {
					skillsMap.put(skill,skillsMap.get(skill)+1);
				}else {
					skillsMap.put(skill,0);
				}
			}
		}
		return skillsMap;
	}	
	
	
	//11. Factorize the YearsExp feature and convert it to numbers in new col. (Bounce )
	
	// TODO
	// read the new csv file
	// YearsExp column
	// factorize (may be use smile in this step)
	// add new column in the same file with factorized data
	// change signature if needed
	@GetMapping("/wuzzuf/YearsExp/fatorize")
	public int[] yearsExpFactorization() throws IOException, URISyntaxException {

		CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
		smile.data.DataFrame df = Read.csv("Wuzzuf_Jobs.csv",format);  
		

		String[] values = df.stringVector("YearsExp").distinct().toArray (new String[]{});
		int[] res = df.stringVector("YearsExp").factorize(  new NominalScale(values) ).toIntArray();

		
		BaseVector bs =   df.stringVector("YearsExp_Factorized").factorize( new NominalScale(values));
		smile.data.DataFrame  YearsExp = smile.data.DataFrame.of(bs);
		
		df = df.merge(df,YearsExp);
		


		File f = java.io.File.createTempFile("Factorized", "csv");
		
		Write.csv(df, f.toPath(), format);
		
		return res;
	}	
	
	
	//12. Apply K-means for job title and companies (Bounce )
	// TODO
	// read the new csv file
	// YearsExp column
	// factorize title and companies (may be use smile in this step)
	// use smile to do  k-mean (or any other package if there is one)
	// we need to formalize the output of the k-means and return it (image / text / .. ) ? 
	// change signature if needed
	@GetMapping("/wuzzuf/kmeans")
	public String kmeans() {

		String res ="test";

		return res;
	}	
	
	
}