package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dao.Wuzzuf;
import joinery.DataFrame;

import smile.data.*;

public class TestingClient {
	static Consumer consumer = new Consumer();
	public static void main(String[] args) {
	
		
//		String summary = consumer.summary();			
//		System.out.print(summary);
//		String head= consumer.head();
//		System.out.println(head);
		
		
		
//		HashMap<String,Integer> skillCount = consumer.skillsCount();
//		skillCount
//         = skillCount.entrySet()
//               .stream()
//               .sorted((i1, i2)
//                           -> i2.getValue().compareTo(
//                               i1.getValue()))
//               .limit(15)
//               .collect(Collectors.toMap(
//                   Map.Entry::getKey,
//                   Map.Entry::getValue,
//                   (e1, e2) -> e1, LinkedHashMap::new));
//		
//		
//		skillCount.entrySet().forEach(entry -> {
//		    System.out.println(entry.getKey() + ":" + entry.getValue());
//		});
//		
		
		

		int[] factorizzedYears = consumer.yearsExpFactorization();
		for (int i : factorizzedYears) {
			System.out.println(i);
		}
		
		
	}

	

	
 
	
}
