package backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.Wuzzuf;
import dao.WuzzufDAOImp;

import joinery.DataFrame;
import smile.io.CSV;

import tech.tablesaw.*;
import tech.tablesaw.api.Table;


@RestController
public class Apis {

	
	//1. Read data set and convert it to dataframe or Spark RDD and display some from it.
	// TODO
	// read the csv file, and display head rows
	// change signature if needed
	@GetMapping("/wuzzuf/head/")
	public String sample() {

		String res ="test";

		return res;
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
		
	

	//3. Clean the data (null, duplications)
	
	// TODO
	// read the csv file, clean it
	// reomove null and duplicate rows
	// change signature if needed
	// save the new file into csv such that we use it in next steps
	@GetMapping("/wuzzuf/clean/")
	public String clean() {

		String res ="test";

		return res;
	}	
	
	
	//4. Count the jobs for each company and display that in order (What are the most demanding companies for jobs?)
	//5. Show step 4 in a pie chart 	
	
	// TODO
	// read the new csv file
	// do step 4
	// check how to return a pie chart (png file) ? 
	// change signature if needed
	@GetMapping("/wuzzuf/jobs/companies")
	public String jobsPerCompany() {

		String res ="test";

		return res;
	}	
	
	
	//6. Find out What are it the most popular job titles? 
	//7. Show step 6 in bar chart
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
	
	
	//8. Find out the most popular areas?
	//9. Show step 8 in bar chart 


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
	
	
	//10. Print skills one by one and how many each repeated and order the output to 
	//find out the most important skills required?
	
	
	// TODO
	// read the new csv file
	// read the skills column
	// split by ','
	// count repitions
	// print top 10 skills with count for each
	// change signature if needed
	@GetMapping("/wuzzuf/skills/count")
	public String skillsCount() {

		String res ="test";

		return res;
	}	
	
	
	//11. Factorize the YearsExp feature and convert it to numbers in new col. (Bounce )
	
	// TODO
	// read the new csv file
	// YearsExp column
	// factorize (may be use smile in this step)
	// add new column in the same file with factorized data
	// change signature if needed
	@GetMapping("/wuzzuf/YearsExp/fatorize")
	public String yearsExpFactorization() {

		String res ="test";

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