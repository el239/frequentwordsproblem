
import java.io.*;
import java.util.Scanner;

public class part1{

private static int count = 0;

public static void main(String args[]){
	   String string1 = new String("");
	   String testString = (mostfrequent("aabaab",2));
	   System.out.println(testString);
	   System.out.println(count);
} // end main
	
public static String mostfrequent(String text, int k){

String word = new String("");

for (int p = 0; p < text.length()-k; p++){ // increments through k number of "slices" of first k-size sequence 
	word = text.substring(p,k+p+1); // takes sequence as a template	
	
	for (int i = 0; i < text.length()-k; i++){
	   int innerCount = 0;
       String block = text.substring(i, i+k+1); // parses text in chunks of size k
       System.out.println(block);
       if (block.equals(word)){
    	   innerCount ++; // increments when "word" finds matches, min. value 1 for when word and block coincide
       } // end if
       
    if (innerCount > count){  
    	count = innerCount;
    } // end if
    
    innerCount = 0;
    
	} // end for
} // end for
return word;
}// end mostfrequent
} // end class