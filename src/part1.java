
import java.io.*;
import java.util.Scanner;

public class part1{

private static int count = 0;

public static void main(String args[]){
	
	   /* to test runtime
	   long startTime = System.nanoTime();
	   String testRunTime = (mostfrequent("variablestringlength",2));
	   long endTime   = System.nanoTime();
	   long totalTime = endTime - startTime;
	   System.out.println(totalTime+ " nanoseconds");
	   */
	
	   String testCase1 = (mostfrequent("aataaaab",2)); // make sure count does not overlap @ aaaa
	   System.out.print(testCase1);
       String testCase2 = (mostfrequent("aabbccbbaacc",2)); // handles tie cases
       System.out.print(testCase2);
       String testCase3 = (mostfrequent("abababab",3)); // handles complex tie cases (overlapping "aba" and "bab")
       System.out.print(testCase3);
       String testCase4 = (mostfrequent("hqwxyz",3)); // handles sequences with no repeats
       System.out.print(testCase4);
       String testCase5 = (mostfrequent("aaaaaaaa",4)); // handles pure repeat sequence
       System.out.print(testCase5);
//	   String errorCase = (mostfrequent("aataaaab",1000)); // make sure exception message is working
//	   System.out.print(errorCase);
       
       String input = "";
	   File text = new File("example.txt"); // file must be present in same directory or given with specified file path
	   try {
	      Scanner s = new Scanner(text);
		  while(s.hasNextLine()) {
			 input = s.nextLine();
		  } // end while
	   } // end try
	   catch (FileNotFoundException e) {
		e.printStackTrace();
	   } // end catch	   
	   
	   System.out.println(mostfrequent(input,4)); // handles files	
	   
} // end main
	
public static String mostfrequent(String text, int k){ 

count = 0;
String word, block = null, mostWord = new String("");
String finalWord = new String("");

if (k > text.length() || k <= 0) // precondition violated
	throw new IllegalArgumentException("Sequence length must be an integer between 1 and text length");

for (int p = 0; p < text.length()-k+1; p++){ // increments through k number of "slices" of first k-size sequence 
	word = text.substring(p,k+p); // takes sequence as a template
	int innerCount = 0;
	
	for (int i = 0; i < text.length()-k+1; i++){
       block = text.substring(i,i+k); // chops text in chunks of size k
       if (block.equals(word)){
    	   
    	   /* debug message
    	   System.out.println("match!");
    	   */ 
    	   
    	   innerCount ++; // increments when "word" finds matches, min. value 1 for when word and block coincide
    	   i += k-1; // VERY IMPORTANT- skips to end of block, i.e., prevents "aaaa" being counted as 3 instances when k=2
           
    	   if (innerCount > count){  
               count = innerCount; // writes over count when a higher frequency sequence is encountered
               mostWord = block; // writes over the the most frequent "word"
               if (finalWord.length() == 0 && count > 1){ // sets initial final word
            	   finalWord = mostWord;
               } // end if
           } // end if
    		
    	    // checks for repeats in "finalWord" before appending
    		if (innerCount == count && innerCount > 1 && !block.equals(finalWord.substring(finalWord.length()-k, finalWord.length()))){
    		   int j;
    		   int appendCount = 0; // marks repeats
    		   for (j = 0; j < finalWord.length()/k; j++) {
    			   if (block.equals(finalWord.substring(j*k,j*k+k))) { // checks for repeats in final word
    				   appendCount++;
    		       } // end if
    		   } // end for
    		   
    			   if (appendCount == 0){
    	 	       finalWord += block; // appends tie cases, if not already in "finalWord"
    			   } // end if
    		       appendCount = 0;
   		    } // end if   
       } // end if
	} // end for 
	
innerCount = 0; // resets match counter
    
} // end for

if (count == 1) // case for no-repeat sequences
	return ("The sequence has no repeat elements.\n");

if (finalWord.length() == k) {
	System.out.print("The highest frequency sequence is " + "\"" + finalWord + "\"" + ", occurring " + count + " times." + "\n");
    return "";
    } // end if
else {
	System.out.print("The highest frequency sequences are ");
	for (int i = 0; i < finalWord.length()/k - 1; i++){ // print formatting
	   System.out.print("\"" + finalWord.substring(i*k,i*k+k) + "\"" + ", ");
	} // end for
	
	if (finalWord.length()/k > 2){ // "each"
	   System.out.print("and " + "\"" + finalWord.substring(finalWord.length()-k,finalWord.length()) + "\"" + ", each occurring " + count + " times." + "\n");
	return(""); 
	} // end if 
	
	else // "both"
	   System.out.print("and " + "\"" + finalWord.substring(finalWord.length()-k,finalWord.length()) + "\"" + ", both occurring " + count + " times." + "\n");
	return("");
} // end else
} // end mostfrequent
} // end class

