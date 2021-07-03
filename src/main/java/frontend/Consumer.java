package frontend;

import java.util.Arrays;
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


	public String jobsPerCompanyPie()
	{
		String jobsPerCompanyPie= restTemplate.getForObject(ROOT_URI+ "jobs/pieChart", String.class);
		return jobsPerCompanyPie;
	}
	
	
	

}
