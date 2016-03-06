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
    	
    	
    	if (args.length != 1) {
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile(args[0]);
    	
    	
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver();

        try 
        {
            List<String> result = wordLadderSolver.computeLadder("heads", "tails");
            boolean correct = wordLadderSolver.validateResult("heads", "tails", result);
            if(correct){
            	System.out.println("\nValidate Result: Valid!!");
            }
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void processLinesInFile(String filename) {

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
    
    public static void createDictionary(String eachline){
    	
    	if(eachline.charAt(0) != '*'){
    		
    		String word = eachline.substring(0,5);
    		dictionary.add(word);
    		
    	}
    	
    	
    }
    
    
}
