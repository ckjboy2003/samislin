/*
 *	Author: I-Sheng Lin
 *	Date: 01/25/2015 
 * 
 **/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class srtConverter {
	public static void log(String message, String filename) throws IOException { 

	      PrintWriter out = new PrintWriter(new FileWriter(filename+".srt"), true);
	      out.write(message);
	      out.close();
	    }
}
