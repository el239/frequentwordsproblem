
import java.io.*;
import java.util.Scanner;
//import java.lang.Throwable;

public class part1{

private static int count = 0;

public static void main(String args[]){
	   String testCase1 = (mostfrequent("aataaaab",2)); // make sure count does not overlap @ aaaa
	   System.out.print(testCase1);
//	   String testCase2 = (mostfrequent("aataaaab",1000)); // make sure exception message is working
       String testCase2 = (mostfrequent("aabbccbbaacc",2)); // handles tie cases
       System.out.print(testCase2);
       String testCase3 = (mostfrequent("abababab",3)); // handles complex tie cases (overlapping "aba" and "bab")
       System.out.print(testCase3);
       String testCase4 = (mostfrequent("hqwxyz",3)); // handles sequences with no repeats
       System.out.print(testCase4);
} // end main
	
public static String mostfrequent(String text, int k){

count = 0;
String word, block = null, mostWord = new String("");
String finalWord = new String("");

if (k > text.length() || k <= 0)
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
               if (finalWord.length() == 0){ // sets initial final word
            	   finalWord = mostWord;
               } // end if
           } // end if
    		
    		if (innerCount == count && !block.equals(finalWord.substring(finalWord.length()-k, finalWord.length()))){
    		   int j;
    		   int appendCount = 0;
    		   for (j = 0; j < finalWord.length()/k; j++) {
    			   if (block.equals(finalWord.substring(j*k,j*k+k))) { // checks for repeats in final word
    				   appendCount++;
    		       } // end if
    		   } // end for
    		   
    			   if (appendCount == 0){
    	 	       finalWord += block;
    			   } // end if
    		       appendCount = 0;
   		    } // end if   
       } // end if
	} // end for 
	
innerCount = 0; // resets match counter
    
} // end for

if (count == 1) // case for no-repeat sequences
	return ("no repetitive sequences found");

if (finalWord.length() == k)
	return ("The highest frequency sequence is " + "\"" + finalWord + "\"" + ", occuring " + count + " times." + "\n");
else {
	System.out.print("The highest frequency sequences are ");
	for (int i = 0; i < finalWord.length()/k - 1; i++){ // print formatting
	   System.out.print("\"" + finalWord.substring(i*k,i*k+k) + "\"" + ", ");
	} // end for
	
	if (finalWord.length()/k > 2){ // "each"
	   System.out.print("and " + "\"" + finalWord.substring(finalWord.length()-k,finalWord.length()) + "\"" + ", each occuring " + count + " times." + "\n");
	return(""); 
	} // end if 
	
	else // "both"
	   System.out.print("and " + "\"" + finalWord.substring(finalWord.length()-k,finalWord.length()) + "\"" + ", both occuring " + count + " times." + "\n");
	return("");
} // end else
} // end mostfrequent
} // end class

