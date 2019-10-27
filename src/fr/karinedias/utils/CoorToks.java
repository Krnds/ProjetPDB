package fr.karinedias.utils;
import java.util.*;  
import java.io.*;
import java.util.regex.Pattern; import
java.io.StreamTokenizer;
import java.nio.charset.StandardCharsets;

/*
 * Class to be deleted, exists for example purposes
 */

public class CoorToks {

    public void StringTokenizer(String token) {
    	
	} //invalid method declaration
    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null; // scan input line by line
        PrintWriter outputStream = null;// output aligned the same way
        Pattern delim=Pattern.compile("/s");

        String token;
        StringTokenizer tokenizer = new StringTokenizer(token);

        try {
        	FileInputStream fileInputStream = new FileInputStream(""); //TODO: put path
        	inputStream = new BufferedReader(new FileReader(fileInputStream));
        	BufferedReader br= new BufferedReader(new FileInputStream(""), StandardCharsets.UTF_8)));
            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));
            while(tokenizer.hasMoreTokens())
            {
                if (token.trim().startsWith("ATOM") && !token.trim().endsWith("H")) // I need to scan for the word "ATOM" before i start tokenizing. ends at H.
                {
                    // and i only need the 7th to 9th tokens of each line.
                    // should i use a pattern delimiter instead?
                    String tokens[]=delim.split(token);
                    double x= Double.parseDouble(tokens[7]);
                    double y= Double.parseDouble(tokens[8]);
                    double z= Double.parseDouble(tokens[9]);
                    outputStream.println(token);

                    //the compiler says it can't find variable tokens. which means i have to do a declaration of variables?
                    // how do i do that when there are so many tokens coming from the text file.
                }
            }
        }//end of try

        finally {
            while ((token = inputStream.readLine()) != null)
            {
                outputStream.println(token);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
