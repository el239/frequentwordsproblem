
import java.io.*;
import java.util.Scanner;
//import java.lang.Throwable;

public class part1 {

private static int count = 0;

public static void main(String args[]){
	   String testCase1 = (mostfrequent("aataaaab",2)); // make sure count is not overlapped @ aaaa
	   System.out.println("The highest frequency sequence is " + "\"" + testCase1 + "\"" + ", occuring " + count + " times.");
//	   String testCase2 = (mostfrequent("aataaaab",1000)); // make sure exception message is working
       String testCase3 = (mostfrequent("aabbxxbbaa",2)); // handles tie cases
       System.out.println("The highest frequency sequence is " + "\"" + testCase3 + "\"" + ", occuring " + count + " times.");
} // end main
	
public static String mostfrequent(String text, int k){

count = 0;
if (k > text.length() || k <= 0){
	throw new IllegalArgumentException("Sequence length must be an integer between 1 and text length");
} // end if

String word, block = null, mostWord = new String("");
String finalWord = new String("");

for (int p = 0; p < text.length()-k+1; p++){ // increments through k number of "slices" of first k-size sequence 
	word = text.substring(p,k+p); // takes sequence as a template
	int innerCount = 0;
	
	// debugging messages
//	System.out.println(word);
//	System.out.println("STARTING INNER LOOP");

	for (int i = 0; i < text.length()-k+1; i++){
       block = text.substring(i, i+k); // parses text in chunks of size k
       // debug message
       System.out.println(block);
       
       if (block.equals(word)){
    	   
    	   /* debug message
    	   System.out.println("match!");
    	   */ 
    	   
    	   innerCount ++; // increments when "word" finds matches, min. value 1 for when word and block coincide
    	   i += k-1; // VERY IMPORTANT- skips to end of block, i.e., prevents "aaaa" being counted as 3 instances when k=2
           
    	   if (innerCount > count){  
               count = innerCount; // writes over count when a higher frequency sequence is encountered
               mostWord = block; // writes over the the most frequent "word"
               innerCount = 0;
               if (finalWord.length() == 0)
            	   finalWord = mostWord;
               } // end if
       } // end if
	} // end for 
    
	System.out.println("THE MOST WORD IS: " + mostWord);
	System.out.println("THE FINAL WORD IS: " + finalWord);

	if (innerCount == count && !mostWord.equals(finalWord.substring(finalWord.length()-k, finalWord.length())) && count > 1){
 	   finalWord += mostWord;
	   } // end if
    innerCount = 0;
} // end for

if (finalWord.length() != 0)
	return finalWord;
} // end mostfrequent
} // end class