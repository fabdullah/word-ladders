package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assign4Driver
{
	public static List<String> dictionary = new ArrayList<String>();
    public static void main(String[] args)
    {	
    	processLinesInDicFile("A4words.dat.txt"); //process dictionary file and create dictionary global list
    	
    	if (args.length != 1) {
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile(args[0]);
    	
    }
	/******************************************************************************
	* Method Name: processLinesInDicFile							   			  *
	* Purpose: This method is designed to look through Dictionary Text file and	  *
	* 		   Create dictionary list											  *
	* Returns: void	                                                         *
	******************************************************************************/
    public static void processLinesInDicFile(String filename) {

		//Translator translator = new Translator();
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				createDictionary(s);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
    
	/******************************************************************************
	* Method Name: processLinesInFile							   			  *
	* Purpose: This method is designed to look through Input Text file and	  *
	* 		   parse input list, and calls computeLadder and validateLadder   *
	* 		   in the WordLadderSolver Class								  *
	* Returns: void	                                                          *
	******************************************************************************/
    public static void processLinesInFile(String filename) {

		//Translator translator = new Translator();
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				String [] start_end = parseInput(s);
				
				  // Create a word ladder solver object
		        Assignment4Interface wordLadderSolver = new WordLadderSolver();

		        try 
		        {
		            List<String> result = wordLadderSolver.computeLadder(start_end[0], start_end[1]);
		            boolean correct = wordLadderSolver.validateResult(start_end[0], start_end[1], result);
		            if(correct){
		            	System.out.println("\nValidate Result: Valid!!");
		            	System.out.println("----------------------------------------------------------");
		            }
		        } 
		        catch (NoSuchLadderException e) 
		        {
		            e.printStackTrace();
		        }
		        
			}//End of the for loop
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	/******************************************************************************
	* Method Name: createDictionary						   			  *
	* Purpose: This method parse dictionary text file				  *
	* 		   														  *
	* Returns: void	                                                  *
	******************************************************************************/
    public static void createDictionary(String eachline){
    	
    	if(eachline.charAt(0) != '*'){
    		
    		String word = eachline.substring(0,5);
    		dictionary.add(word);
    		
    	}
    	
    	
    }
	/******************************************************************************
	* Method Name: parseInput						   			  *
	* Purpose: This method parse input textfile					  *
	* 													 		  *
	* Returns: String[]: contain starts and end                   *
	******************************************************************************/
    public static String[] parseInput(String s){
    	String[] result = s.split("\\s+");
    	return result;
    }
    
    
}
