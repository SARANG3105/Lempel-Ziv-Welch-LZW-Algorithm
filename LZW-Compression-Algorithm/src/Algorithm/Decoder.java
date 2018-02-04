


package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Decoder {

	public static void main(String[] args) {

		System.out.println("Enter file path to decode :");
		Scanner sc = new Scanner(System.in);
		File file = new File(sc.nextLine());
		String filename = file.getName();
		BufferedWriter writer = null;
		int dictionarySize = 256;
		int p = filename.lastIndexOf('.');
		if (p > 0) {
			filename = filename.substring(0, p);
		}
		HashMap<Integer, String> dictionary = new HashMap<Integer, String>();

		for (int i = 0; i < dictionarySize; i++) {
			dictionary.put(i, "" + (char) i);

		}

		double max_table_size = Math.pow(2, 12);
		int dictionary_size = dictionary.size();
		int code = 0;
		String str = "";
		try (FileInputStream fn = new FileInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fn, StandardCharsets.UTF_16BE))) {

		while((code = reader.read())>0){
			 
			
			if (str == "") {
				if (dictionary.containsKey(code)) {
					str = dictionary.get(code);
				//	System.out.println(str);
				}
			}

			else if (code > 0 && str != "") {
				String newStr = "";
				String new_string = "";

				if (!dictionary.containsKey(code)) {
					new_string = str + str.charAt(0);
					//System.out.println(new_string);
				} else {
					new_string = dictionary.get(code);
					//System.out.println("newStr - " + new_string);
				}

				/*
				 * If TABLE.size < MAX_TABLE_SIZE: // if table is not full
				 * 
				 */
				if (dictionary_size < max_table_size) {
					
					dictionary.put(dictionary_size++, (str + new_string.charAt(0)));
					dictionary_size = dictionary.size();

					
					str = new_string;
					//System.out.println(str);
				}

			}
			
			
			try {
				if(writer==null){	
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename + "_decoded.txt"))); 
				}
					writer.write(str);
				} catch (Exception e) {
					System.out.println("Exception occured in file write. " + e.getMessage());
				}
		}
			//writer.close();

		} catch (Exception e) {
			System.err.println(e);

		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file decoded: "+filename+"_decoded.txt");
		}
	}
}
