//Ian Smith
//9/15/2019
//Smith_text_analyzer
//Purpose: reads a file and outputs the top 20 words and the number of occurrences
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Smith_text_analyzer {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		
		//create hashmap to hold words from the input file and the number of times they occur
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		
		try 
		{
			//create buffered reader to obtain input file
			BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\JAVA_GOD\\Desktop\\Macbeth.txt"));
		
			//begin reading first line of input file
			String thisLine = read.readLine();
			
			
			while(thisLine != null)
			{
				//remove punctuation and split words with whitespace
				String[] words = thisLine.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
				
				//iterate through string to find words
				for(String word : words) {
					
					//check to see if map contains word as a key
					if(wordMap.containsKey(word))
					{
						// increment keys value by 1 if word is already found in map
						wordMap.put(word, wordMap.get(word) + 1);
					}
					
					//if word was not contained in map, add word and set occurrence value to 1
					else
					{
						wordMap.put(word, 1);
					}
				}
				
				//move reader to next line in string from input file
				thisLine = read.readLine();
				
			}
			
			//put wordMap into ArrayList for sorting and comparing values
			List<Map.Entry> list = new ArrayList(wordMap.entrySet());
			
			//use Collections sort method to compare and sort keys and values
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					Integer value1 = (Integer)((Map.Entry) o1).getValue();
					Integer value2 = (Integer)((Map.Entry) o2).getValue();
						return value1.compareTo(value2);
				}
			});
			
			
			
		
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
}
