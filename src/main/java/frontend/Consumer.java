package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import dao.Wuzzuf;
import joinery.DataFrame;
import tech.tablesaw.plotly.components.Figure;

public class Consumer {

	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	final String ROOT_URI = "http://localhost:8080/wuzzuf/";

	// to be updated
	public String head() {
		String head = restTemplate.getForObject(ROOT_URI+"head", String.class);
		return head;

	}
	
	
	public String summary() {
		String summary = restTemplate.getForObject(ROOT_URI+"summary", String.class);
		return summary;

	}	
	

	public HashMap<String,Integer> skillsCount() {
		HashMap<String,Integer> skills = restTemplate.getForObject(ROOT_URI+"skills/count", HashMap.class);
		return skills;

	}	
	
	public int[] yearsExpFactorization() {
		int[] factorizzedYears = restTemplate.getForObject(ROOT_URI+"YearsExp/fatorize", int[].class);
		return factorizzedYears;

	}	
	

	public String clean()
	{
		String clean= restTemplate.getForObject(ROOT_URI+"clean", String.class);
		return clean;
	}

	public String jobsPerCompany()
	{
		String jobsPerCompany= restTemplate.getForObject(ROOT_URI+ "jobs/companies", String.class);
		return jobsPerCompany;
	}


	public HashMap<String,Double> jobsPerCompanyPie()
	{
		HashMap<String,Double> jobsPerCompanyPie= restTemplate.getForObject(ROOT_URI+ "jobs/pieChart", HashMap.class);
		return jobsPerCompanyPie;
	}
	
	
	

}
