/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
  
	List<String> dic = Assign4Driver.dictionary;
	Stack<String> solution = new Stack<String>();
	List<String> visited = new ArrayList<String>();

    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        // implement this method
		if (startWord.length() == 5 && checkWordInDictionary(startWord)) {
			if (endWord.length() == 5 && checkWordInDictionary(endWord)) {
				if (MakeLadder(startWord, endWord, -1)) {

					//System.out.println(solution);
					for(int i=0; i<solution.size(); i++){
						System.out.println(solution.get(i));
					}
					return solution;

				}
				else{
					System.out.println("There is no word ladder between " + startWord +" and " + endWord);
				}

			} 
			else {
				System.out.println("At least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary");// input exception: endWord is not valid (length is not 5 and word is not in dictionary)
				System.out.println();
			}

		} 
		else {
			System.out.println("At least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary");// input exception: startWord is not valid (length is not 5 and word is not in dictionary)
			System.out.println();
		}
    	
    	
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	
    	//Checking if all the words in wordLadder is exist in the dictionary
    	for(int i=0; i<wordLadder.size(); i++){
    		boolean check = checkWordInDictionary(wordLadder.get(i));
    		if(check == false){
    			return false;
    		}
    	}
    	
    	//Checking if words in the wordLadder is one character away from each other
    	for(int i=1; i<wordLadder.size(); i++){
    		int counter = 0;
    		for(int j=0; j<5;j++){
    
    			if(wordLadder.get(i-1).charAt(j) == wordLadder.get(i).charAt(j)){
    				counter++;
    			}
    		}
    		
    		if(counter != 4){
				return false;
			}
    		
    	}
    	return true;
    	
        //throw new UnsupportedOperationException("Not implemented yet!");
    }

 
/*
 * 
 *     
 * 
 *     
 */   
	public boolean MakeLadder(String start, String end, int position) {
		
		solution.push(start);
		
		/*
		 * Base case: check start is one letter different in the beginning
		 */
		int counter = 0;
		for(int i=0; i<start.length(); i++){
			if(start.charAt(i) == end.charAt(i)){
				counter++;
			}
		}
		if(counter ==4){
			solution.push(end);
			return true;
		}
		
		//Termination Case: when you find word that is one character difference from the end, you found solution!!
		ArrayList<String> temp_list = diffbyone(start, end, position);
		if (!temp_list.isEmpty()) {

			if (temp_list.get(0).charAt(0) == '4') {
				solution.push(temp_list.get(0).substring(1));
				solution.push(end);
				return true;
			}

			for (int i = 0; i < temp_list.size(); i++) {
				int new_position = findDifferentIndex(start, temp_list.get(i).substring(1));
				String new_word = temp_list.get(i).substring(1);
				//Recursive step!
				if (MakeLadder(new_word, end, new_position)) {
					return true;
				}
			}
		}
		//Cannot find WordLadder, so pop the start in the solution list
		solution.pop();
		
		return false;

	}// end of the MakeLadder
    
    /*
     * check if list contain the end(or final) word in the ladder
     * if existed in the list return true
     */
    public boolean checkFinalWord(String end, ArrayList<String> list){
    	boolean flag = false;
    	
    	for(int i=0; i<list.size(); i++){
    		if(list.get(i).equals(end)){
    			flag = true;
    		}
    	}
    	
    	return flag;
    }
  
/*
 * This method will check if word is existed in the dictionary
 * if exist in the dictionary, return true    
 */
    public boolean checkWordInDictionary(String word){
    	
    	boolean flag = false;
    	
    	for(int i=0; i<dic.size();i++){
    		if(dic.get(i).equals(word)){
    			flag = true;
    		}	
    	}
		return flag;
    }
    
/*
 * This method will count same characters when compare with end word
 * return ArrayList of numbers of same character   
 */
    public ArrayList<Integer> findEnd(String end, ArrayList<String> list){
    	ArrayList<Integer> countlist = new ArrayList<Integer>();
    	
    	for(int i=0;i<list.size();i++){
    		String currentWord = list.get(i);
    		
    		int counter = 0;
    		for(int j=0;j<end.length();j++){
    			if(currentWord.charAt(j) == end.charAt(j)){
    				counter++;
    			}
    		}
    		countlist.add(counter);
    	}
    	
    	return countlist;
    }

/*
 * This method find index of difference compared to start word.
 */
    public int findDifferentIndex(String start, String word){
    	int index = -1;
    	
    	for(int i=0; i<start.length(); i++){
    		if(start.charAt(i) != word.charAt(i)){
    			index = i;
    		}
    	}
    	
    	return index;
    }
    
    public ArrayList<String> diffbyone(String start, String end, int position){
    	ArrayList<String> temp_list = new ArrayList<String>();
    	//This for-loop add one letter difference word to temp_list
    	for(int i=0; i<dic.size(); i++){
    		int counter = 0;
    		for(int j=0; j<start.length();j++){
    	
    			if(start.charAt(j) == dic.get(i).charAt(j)){
    				counter++;
    			}
    			
    		}
    		
    		if(counter == 4 && !start.equals(dic.get(i))){
    			if(!solution.contains(dic.get(i)))
				temp_list.add(dic.get(i));
			}
    	}
    	//This for-loop consider position into account. delete all the word that is one letter different from position given.
		if (position != -1) {
			for (int i = 0; i < temp_list.size(); i++) {
				String str = temp_list.get(i);
				str = str.substring(0, position) + str.substring(position + 1);
				String startstr = start.substring(0, position) + start.substring(position + 1);

				if (str.equals(startstr)) {
					temp_list.remove(i);
					i--;
				}
			}
		}

		ArrayList<Integer> temp_list_integer = findEnd(end, temp_list); //find integer list on how similar to end word
    	
		//prepend
		for(int i=0; i<temp_list.size(); i++){
			String num = temp_list_integer.get(i).toString();
			temp_list.set(i, num+temp_list.get(i));	
		}
		Collections.sort(temp_list);
		Collections.reverse(temp_list);
    	return temp_list;
    }

  
}
