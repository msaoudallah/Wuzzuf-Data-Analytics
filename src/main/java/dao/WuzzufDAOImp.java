package dao;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.lf5.util.ResourceUtils;

import joinery.DataFrame;

public class WuzzufDAOImp {

	DataFrame ReadFile(String filenme) throws IOException {
		
		DataFrame df = DataFrame.readCsv(filenme);

		return df;
	}
	
	
	List<Wuzzuf> getALL(DataFrame df){
		
		List<Wuzzuf> jobs = new ArrayList<Wuzzuf>();
		
		for ( int i = 0 ; i<df.length(); i++) {
			List<Object> lo = 	df.row(i);
			String Title = (String) lo.get(0);
			String  Company=(String) lo.get(1);
			String  Location=(String) lo.get(2);
			String  Type=(String) lo.get(3);
			String  Level=(String) lo.get(4);
			String  YearsExp=(String) lo.get(5);
			String  Country=(String) lo.get(6);
			String  Skills=(String) lo.get(7);
			
			Wuzzuf w = new Wuzzuf(Title, Company, Location, Type, Level, YearsExp, Country, Skills);
			jobs.add(w);
			
		}
		
		return jobs;
		
		
		
	}
	
}
