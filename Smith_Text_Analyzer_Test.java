/**@author Ian Smith
 * @created 10/24/2019
 * @purpose test cases used to test the functionality of methods in the Smith_Text_Analyzer.java file
*/
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class Smith_Text_Analyzer_Test {

	@Test
	void evaluateLineTest() {
		//creates string to be evaluated for the test, including capitalization and punctuation
		String testLine = "The, thE the test test! is live";
		HashMap<String, Integer> testMap = new HashMap<String,Integer>();
		
		//run evaluateLine(String, HashMap<String,Integer) method on test String 
		//returning key/value pairs to testMap to prepare for comparison
		Smith_Text_Analyzer.evaluateLine(testLine, testMap);
		
		//create and populate a second map with expected test results for comparison
		HashMap<String, Integer> comparedMap = new HashMap<String,Integer>();
		comparedMap.put("the", 3);
		comparedMap.put("test", 2);
		comparedMap.put("is", 1);
		comparedMap.put("live", 1);
		
		//compares the two HashMaps contents and completes testS
		assertEquals(testMap.equals(comparedMap), true);
	}
	
	@Test
	void sortMapTest() {
		//creates a hashMap with no apparent order in key/value placement on index
		HashMap<String, Integer> unorderedMap = new HashMap<String,Integer>();
		unorderedMap.put("is", 1);
		unorderedMap.put("the", 3);
		unorderedMap.put("test", 2);
		unorderedMap.put("live", 1);
		//creates a hashmap with a descending order in the values column to compare
		//unorderedMap to after it has been passed to the sortMap(HashMap<String, Integer>) method
		HashMap<String, Integer> orderedMap = new HashMap<String,Integer>();
		orderedMap.put("the", 3);
		orderedMap.put("test", 2);
		orderedMap.put("is", 1);
		orderedMap.put("live", 1);
		//calls sortMap method passing unorderedMap as the argument and determines test results
		Smith_Text_Analyzer.sortMap(unorderedMap);
		assertEquals(unorderedMap.equals(orderedMap), true);
	}

}
