
/**@author Ian Smith
 * @version 1.0.1
 * @created 9/15/2019
 * @purpose reads a text file and outputs the top used words and number of occurrences
 * */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Smith_Text_Analyzer extends Application{
	

	final static String FILE = "C:\\\\Users\\\\JAVA_GOD\\\\Desktop\\\\Macbeth.txt"; //File to read
	static ListView<String> listView; 
	
	
	
	public static void main(String args[]) {
		
		
		
		
		//hashmap to hold word and value pairs
		//String for word and Integer for number of occurrences
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

		String line; //holds input line in String variable when used
		
		//Create File object using constant variable declared earlier
		//Create Scanner object to read File
		//Catch IOException if exceptions are thrown
		try (Scanner reader = new Scanner(new File(FILE))){
			//read file to scanner one line at a time with hasNextLine()
			while(reader.hasNextLine()) {
				line = reader.nextLine();
				
				if(line != null) {
					//if line is not null, call the evaluateLine method with line and hashmap
					evaluateLine(line, wordMap);
				}
			}
		} catch(IOException ex) {
			System.out.println(ex);
		}
			
		//create a second map to call the sortmap method and store the sorted wordmap by the number
		//of occurrences of that word
		Map<String, Integer> sortedWordMap = sortMap(wordMap);
		
		
		// Print each word from the sorted map and the number of occurrences of that word
		System.out.println("Words appearing in " + FILE + " and the number of times they occur:\n"
				+ "(note: words have all been converted to lower case for accurate counting)\n");
		sortedWordMap.forEach((word, wordCount) -> System.out.println(word + " " + wordCount));
		
		//instantiating and filling listView for Start Method
		listView = new ListView<String>();
		ArrayList<String> keyValuePairs = new ArrayList(sortedWordMap.entrySet());
		String replacedKeyValuePairs = keyValuePairs.toString().replaceAll("="," = ").replaceAll("\\*/-,\\]\\[", "");
		String[] newKeyValuePairs = replacedKeyValuePairs.split(",");
		listView.getItems().addAll(newKeyValuePairs);
		
		
			
		Application.launch(args);
		
	}

	
	/**Evaluates an array of String values created from the text file and cleans out any punctuation. 
	 * Then checks if the String is in the occurrences Map and updates the occurrences count.
	 * @param line The reader object being used to evaluate the desired text file.
	 * @param occurrences The HashMap containing words and their number of occurrences to be updated.
	 * 
	 * */
	public static void evaluateLine(String line, HashMap<String, Integer> occurrences) {
		//Create an Array of String to store the words split by whitespace from the line variable
		String[] words = line.split("\\s+");

		//loop through the words in the String Array
		for(int i = 0; i < words.length; i++) {

			//the current word to evaluate
			String key = words[i]; 

			// Check if the current word ends with a common sentence punctuation
			// If so, remove the punctuation from the string
			if (key.length() > 0) {
				switch (key.charAt(key.length() - 1)) {
				case '.':
				case '?':
				case '!':
				case '*':
				case '[':
				case ']':
				case ',':
				case ';':
				case ':':
				case '-':
				case '/':
					key = key.substring(0, key.length() - 1);
					break;
				default:
					break;
				}
			}

			// Confirm the word has a length greater than 0
			// before operating on it
			if (key.length() > 0) {
				key = key.toLowerCase(); // Change to lower case for accurate counting

				// Check if the Map already contains the word.
				// If so, get the current count, add 1, and add the word back to the map
				// If not, add word to the map with a count of 1
				if (occurrences.containsKey(key)) {
					Integer count = occurrences.get(key);
					count++;
					occurrences.put(key, count);
				} else {
					occurrences.put(key, 1);
				}
			}
		}

	}
	
	/**Accepts a HashMap of word keys and occurrence values in no particular order and uses the Collections class to sort the HashMap in descending order. 
	 * @param wordListToSort map containing the words and occurrences in no type of order.
	 * @return sortedMap map containing words and occurrences in descending order.
	 * */
	public static Map<String, Integer> sortMap(HashMap<String, Integer> wordListToSort) {
		
		//create a new LinkedList from all entries passed to the map
		List<Map.Entry<String, Integer>> sortedList = new LinkedList<Map.Entry<String, Integer>>
		(wordListToSort.entrySet());

		// Sort the list by value
		// The order used by compareTo ensures list is sorted with largest to smallest value
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> firstEntry, Map.Entry<String, Integer> secondEntry) {
				return (secondEntry.getValue()).compareTo(firstEntry.getValue());
			}
		});

		
		// Store the sorted list into a new LinkedHashMap and then return the LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : sortedList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		//adds sorted map key values to list view
		
		
		
		return sortedMap;
	}
	
	


	/**Overridden start method used to display a basic GUI with a label showing the file being analyzed as well as a listview containing all of the 
	 * words and their occurrences in descending order.
	 * */
	@Override
	public void start(Stage primaryStage) throws Exception {
		//creating a pane and settings properties
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		//creating ListView
		
		
			listView.setPrefWidth(100);
		
		//placing necessary nodes in the pane1
		pane.add(new Label("File being analyzed: "), 0, 0);
		pane.add(new Label(FILE), 1, 0);
		pane.add(new Label("Word Occurrences: "), 0, 1);
		pane.add(listView, 1, 1);
		
		
		
		//creating a scene to place in stage
		Scene scene = new Scene(pane, 400, 450);
		primaryStage.setTitle("Text Analyzer");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}


	/**Evaluates an array of String values created from the text file and cleans out any punctuation. 
	 * Then checks if the String is in the occurrences Map and updates the occurrences count.
	 * @param line The reader object being used to evaluate the desired text file.
	 * @param occurrences The HashMap containing words and their number of occurrences to be updated.
	 * 
	 * */
	public static void evaluateLine(String line, HashMap<String, Integer> occurrences) {
		//Create an Array of String to store the words split by whitespace from the line variable
		String[] words = line.split("\\s+");
	
		//loop through the words in the String Array
		for(int i = 0; i < words.length; i++) {
	
			//the current word to evaluate
			String key = words[i]; 
	
			// Check if the current word ends with a common sentence punctuation
			// If so, remove the punctuation from the string
			if (key.length() > 0) {
				switch (key.charAt(key.length() - 1)) {
				case '.':
				case '?':
				case '!':
				case '*':
				case '[':
				case ']':
				case ',':
				case ';':
				case ':':
				case '-':
				case '/':
					key = key.substring(0, key.length() - 1);
					break;
				default:
					break;
				}
			}
	
			// Confirm the word has a length greater than 0
			// before operating on it
			if (key.length() > 0) {
				key = key.toLowerCase(); // Change to lower case for accurate counting
	
				// Check if the Map already contains the word.
				// If so, get the current count, add 1, and add the word back to the map
				// If not, add word to the map with a count of 1
				if (occurrences.containsKey(key)) {
					Integer count = occurrences.get(key);
					count++;
					occurrences.put(key, count);
				} else {
					occurrences.put(key, 1);
				}
			}
		}
	
	}

	

}



