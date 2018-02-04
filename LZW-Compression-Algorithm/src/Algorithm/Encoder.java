

package Algorithm;


import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.util.HashMap;



public class Encoder {

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter file path to Encode: \n");
			File file = new File(sc.nextLine());
			String filename = file.getName();

			int p = filename.lastIndexOf('.');
			if (p > 0) {
				filename = filename.substring(0, p);
			}
			String strng = "";
			int tableSize = 256;

			HashMap<String, Integer> table = new HashMap<String, Integer>();
			for (int i = 0; i < tableSize; i++) {
				table.put("" + (char) i, i);

			}

			double max_table_size = Math.pow(2, 12);

			int table_size = table.size();
	

			System.out.println("Input File :- " + file.getName());

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line = null;

				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(filename + ".lzw"),StandardCharsets.UTF_16BE));
			
					} catch (Exception e) {
					System.out.println("Exception occured in file write. " + e.getMessage());
				}
				// while there are still input symbols:
				while ((line = reader.readLine()) != null) {
					// System.out.println("line -"+ line);
					for (char symbol : line.toCharArray()) {

						/*
						 * SYMBOL = get input symbol if STRING + SYMBOL is in TABLE:
						 * STRING = STRING + SYMBOL
						 * 
						 */
						if (table.containsKey((strng + symbol))) {
							strng = strng + symbol;

						} else {
							// else:output the code for STRING

							if (strng != "") {
								// tSystem.out.println("str-"+dictionary.get(str));
								try {
									
									writer.write(table.get(strng));
								
									} catch (Exception e) {
									System.out.println("Exception occured in file write. " + e.getMessage());
									writer.close();
								}
							}

							/*
							 * If TABLE.size < MAX_TABLE_SIZE: // if table is not
							 * full
							 * 
							 */
							if (table_size < max_table_size) {
								// add STRING + SYMBOL to TABLE // STRING + SYMBOL
								// now has a code
								table.put((strng + symbol), tableSize++);
								table_size = table.size();

								// STRING = SYMBOL
								strng = symbol + "";

								// output the code for STRING
								// System.out.println(dictionary.get(str));

							}
						
						}
					}
					if (strng != "") {
					
						try {	
					
							writer.write(table.get(strng));
							
							} catch (Exception e) {
							System.out.println("Exception occured in file write. " + e.getMessage());
							writer.close();
						}
						System.out.println("File Is Encoded");
						System.out.println("Output file:- "+filename+".lzw");
						
					}
				
							writer.close();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}
