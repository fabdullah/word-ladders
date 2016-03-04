/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
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
    	MakeLadder("stone", "money", -1);
    	System.out.println(solution);
    	System.out.println();
    	
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    
    public void MakeLadder(String start, String end, int position){
    	
    	if(checkWordInDictionary(start) && !solution.contains(start)){
    		solution.push(start);
    	}
    
    	
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
		boolean flag = checkWordInDictionary(end);
		boolean flag2 = checkFinalWord(end,temp_list);
	
		if(flag&&flag2){
			solution.push(end);
			return;
		}

	
		
		ArrayList<Integer> temp_list_integer = findEnd(end, temp_list);
		ArrayList<Integer> temp_list_integer_sort = new ArrayList<Integer>();
		
		temp_list_integer_sort.addAll(temp_list_integer);
		Collections.sort(temp_list_integer_sort);
		Collections.reverse(temp_list_integer_sort);
		
		
		int max = Collections.max(temp_list_integer);
		
		ArrayList<String> temp_list_sort = new ArrayList<String>();
		while(max>=0){
			for(int i=0; i<temp_list.size();i++){
				if(temp_list_integer.get(i) == max){
					temp_list_sort.add(temp_list.get(i));
				}
			}
			max--;
		}

		
/*
 * Recursive
 */
		max = Collections.max(temp_list_integer);
		for(int i=0;i<temp_list_sort.size();i++){
			String word = temp_list_sort.get(i);
			max = Collections.max(temp_list_integer);
			
			int index_position = findDifferentIndex(start, word);
			if(!solution.contains(word) && temp_list_integer_sort.get(i)>=max){
				MakeLadder(word, end, index_position);
			}
			else{
				solution.pop();
			}
			
		}
		
		System.out.println();
/*		
		for(int a=0;a<temp_list_integer.size();a++){
			if(temp_list_integer.get(a) == max){
				temp_list_max.add(temp_list.get(a));
			}
		}
*/		
		
/*		
		ArrayList<String> visited = new ArrayList<String>();
		for(int i=0;i<temp_list_max.size();i++){
			int index_position = findDifferentIndex(start, temp_list_max.get(i));
			MakeLadder(temp_list_max.get(i), end, index_position);
			
		}
*/		

		
    
    }//end of the MakeLadder
    
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

  
}
