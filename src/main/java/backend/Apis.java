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

	@GetMapping("/wuzzuf/summary")
	public String summary() throws IOException, URISyntaxException {

		String res ="";

		Table df = Table.read().csv("Wuzzuf_Jobs.csv");
		
		res += df.summary().toString();
		
		return res;
	}
		
	@GetMapping("/wuzzuf/test")
	public String test() {

		String res ="test";

		return res;
	}	
	
	
//	@GetMapping("/wuzzuf/jobs")
//	public DataFrame jobs() throws IOException {
//		WuzzufDAOImp WD = new WuzzufDAOImp();
//
//		DataFrame df = WD.ReadFile("Wuzzuf_Jobs.csv");
//		df = df.retain("Title","Company")
//		.groupBy("Company")
//		.count()
//		.sortBy("Title");	
//		
//		return df;
//
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@GetMapping("/wuzzuf/all")
//	public List<Wuzzuf> hello() throws IOException {
//		WuzzufDAOImp WD = new WuzzufDAOImp();
//		DataFrame df = WD.ReadFile("Wuzzuf_Jobs.csv");
//		List<Wuzzuf> dflist = WD.getALL(df);
//
//		//String x = df.toString();
//		return dflist;
//
//	}	



	
	
}