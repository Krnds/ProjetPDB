package main.fr.karinedias.query;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DownloadPDB2 {

	/*
	 * Class for accessing pdb file and download them as a .cif file
	 */
	
	public static void main(String[] args) {
		
		//variables :
		URL url;
		URLConnection connection;
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter the ID of the structure to download :\n");
			String id = sc.nextLine();
			
			String s = "https://files.rcsb.org/view/" + id + ".cif";
			url = new java.net.URL(s);
			
			connection = url.openConnection(); // open the url connection.

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); 
            
            // Enter filename in which you want to download 
            BufferedWriter writer =  new BufferedWriter(new FileWriter(id + ".cif")); 
              
            // read each line from stream till end 
            String line; 
            while ((line = reader.readLine()) != null) { 
                writer.write(line); 
                writer.newLine();
            } 
  
            reader.close(); 
            writer.close(); 
            System.out.println("Successfully Downloaded."); 
			
		} catch (java.util.InputMismatchException | UnsupportedEncodingException e) {
			sc.nextLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();

	}
}
