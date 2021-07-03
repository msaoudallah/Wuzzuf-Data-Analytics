package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.Wuzzuf;
import joinery.DataFrame;

import smile.data.*;
import tech.tablesaw.plotly.components.Figure;

public class TestingClient {
	static Consumer consumer = new Consumer();
	public static void main(String[] args) {
	
		
//		String summary = consumer.summary();
//		System.out.print(summary);
//		String head= consumer.head();
//		System.out.println(head);

//		String clean= consumer.clean();
//		System.out.println(clean);


//		String jobsPerCompany= consumer.jobsPerCompany();
//		System.out.println(jobsPerCompany);


		String t= consumer.jobsPerCompanyPie();
		System.out.println(t);



	}

	

	
 
	
}
