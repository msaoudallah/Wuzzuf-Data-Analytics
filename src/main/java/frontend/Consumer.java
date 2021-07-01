package frontend;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import dao.Wuzzuf;
import joinery.DataFrame;

public class Consumer {

	
	
	@Autowired
	RestTemplate restTemplate = new RestTemplate();

	final String ROOT_URI = "http://localhost:8080/wuzzuf/";

	public List<Wuzzuf> getAllJobs() {
		Wuzzuf[] wl = restTemplate.getForObject(ROOT_URI+"all", Wuzzuf[].class);
		return Arrays.asList(wl);
	}
	
	public DataFrame jobs() {
		DataFrame df = restTemplate.getForObject(ROOT_URI+"jobs", DataFrame.class);
		return df;

	}	

	public String summary() {
		String summary = restTemplate.getForObject(ROOT_URI+"summary", String.class);
		return summary;

	}	

}
