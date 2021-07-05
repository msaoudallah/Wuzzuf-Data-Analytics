package backend;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static tech.tablesaw.aggregate.AggregateFunctions.*;

//import tech.tablesaw.*;
//import smile.plot.swing.Plot;
import tech.tablesaw.api.*;
import smile.data.measure.NominalScale;
import smile.io.Read;
import tech.tablesaw.columns.Column;
//import tech.tablesaw.*;
import tech.tablesaw.aggregate.AggregateFunctions;
import tech.tablesaw.aggregate.Summarizer;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;


@RestController
public class Apis {


	@GetMapping("/wuzzuf/head")
	public String head() throws IOException
	{
		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		Table head= df.first(5);
		return head.toString();
	}	
	

		@GetMapping("/wuzzuf/summary")
	public String summary() throws IOException, URISyntaxException {

		String res ="";

		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		
		res += df.shape();
		res += "\n\n\n";		
		res += df.summary().toString();
		return res;
	}
		
	

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
	

	@GetMapping("/wuzzuf/jobs/companies")
	public String jobsPerCompany() throws IOException
	{
		Table df= cleaned_df();
		Table jobsPerCompany= df.retainColumns("Company", "Title");
		Table summary= jobsPerCompany.summarize("Title",  count).by("Company");
		Table summary1= summary.sortDescendingOn("Count [Title]");
		return summary1.toString();//+ "\n "+ cout_summ;

	}

	@GetMapping("/wuzzuf/jobs/pieChart")
	public LinkedHashMap<String,Double> jobsPerCompanyPie() throws IOException
	{
		Table df= cleaned_df();
		Table jobsPerCompany= df.retainColumns("Company", "Title");
		Table summary= jobsPerCompany.summarize("Title",  count).by("Company");
		Table summary1= summary.sortDescendingOn("Count [Title]");
		Table tablePieCharted= summary1.first(10);
		Double PieCount = (Double) tablePieCharted.summarize("Count [Title]",sum).apply().get(0, 0);
		Double OtherCount = df.rowCount() - PieCount; 
		
		LinkedHashMap<String,Double> hm = new LinkedHashMap<String,Double> ();
		for (Row row : tablePieCharted) {
			hm.put(row.getString("Company"),row.getDouble("Count [Title]"));
		}
		hm.put("Other(Companies wth less than 10 jobs)",OtherCount);

		return hm;
	}
	

	@GetMapping("/wuzzuf/jobs/count")
	public LinkedHashMap<String,Integer> jobsCount() throws IOException, URISyntaxException {
		Table wuzzuf_jobs = cleaned_df();
		Table results = wuzzuf_jobs.countBy("Title").sortDescendingOn("Count").first(10);
		LinkedHashMap<String,Integer> intermediate = new LinkedHashMap<>();
		for (Row i:results)
		{
			intermediate.put(i.getString("Category"),i.getInt("Count"));
		}
		return intermediate;
	}	
	

	@GetMapping("/wuzzuf/areas/count")
	public LinkedHashMap<String,Integer> areasCount() throws IOException, URISyntaxException {

		Table wuzzuf_jobs = cleaned_df();
		Table results = wuzzuf_jobs.countBy("Location").sortDescendingOn("Count").first(10);
		LinkedHashMap<String,Integer> intermediate = new LinkedHashMap<>();
		for (Row i:results)
		{
			intermediate.put(i.getString("Category"),i.getInt("Count"));
		}
		return intermediate;
	}	
	

	@GetMapping("/wuzzuf/skills/count")
	public HashMap<String,Integer> skillsCount() throws IOException {

		Table df = cleaned_df();
		Column<String> skills = (Column<String>) df.column("Skills");

		HashMap<String,Integer> skillsMap = new HashMap<String,Integer> ();
		for (String s : skills) {
			String[] jobskills = s.split(", ");
			for (String skill: jobskills) {
				if (skillsMap.containsKey(skill)) {
					skillsMap.put(skill,skillsMap.get(skill)+1);
				}else {
					skillsMap.put(skill,1);
				}
			}
		}
		return skillsMap;
	}	
	

	@GetMapping("/wuzzuf/YearsExp/fatorize")
	public String yearsExpFactorization() throws IOException, URISyntaxException {

		CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
		smile.data.DataFrame df = Read.csv("Wuzzuf_Jobs.csv",format);
		String[] values = df.stringVector("YearsExp").distinct().toArray (new String[]{});
		int [] YearExpValues = df.stringVector("YearsExp").factorize(  new NominalScale(values) ).toIntArray();
		Table wuzzuf_jobs = Table.read().csv("Wuzzuf_Jobs.csv");
		IntColumn ic = IntColumn.create("YearsExp_Factorized", YearExpValues);
		wuzzuf_jobs.addColumns(ic);	
		String res = wuzzuf_jobs.sampleN(5).toString();
		return res;
	}	
	@GetMapping("/wuzzuf/kmeans")
	public double[][] kmeans() throws IOException, URISyntaxException {

		CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
		smile.data.DataFrame df = Read.csv("Wuzzuf_Jobs.csv",format);
		String[] Companies = df.stringVector("Company").distinct().toArray (new String[]{});
		String[] Title = df.stringVector("Title").distinct().toArray (new String[]{});
		double [] CompaniesValues = df.stringVector("Company").factorize(  new NominalScale(Companies) ).toDoubleArray();
		double [] TitleValues = df.stringVector("Title").factorize(  new NominalScale(Title) ).toDoubleArray();
		double[][] kmeansData = new double[CompaniesValues.length][2];
		for (int i = 0 ; i < CompaniesValues.length; i++) {
			kmeansData[i][0] = CompaniesValues[i];
			kmeansData[i][1] = TitleValues[i];
		 }
		

		return kmeansData;
		
		
		
	}	
	
	
}