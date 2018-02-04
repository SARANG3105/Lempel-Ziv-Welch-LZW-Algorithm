

==DESCRIPTION==

-I have written Encoder and decoder files for this algorithm using JAVA language using java version "1.8.0_121"and
Java(TM) SE Runtime Environment (build 1.8.0_121-b13). 
-The Datastructure used in this program is a hashmap it allows us to store object and retrieve it in constant time O(1) . In hashing, hash functions are used to link key and value in HashMap.Objects are added using PUT command and retrieved using GET. Both encoder and decoder have there own hashmaps.



==PROGRAM DESIGN==
Encoder:-
-Implemented scanner class to get user input as file path
-Implemented bufferreader to read file .
-The underlying algorithm is derived from the pseudocode provided as an example. 
-while writing the files to .lzw format i used standard charset UTF-16BE to save the output as 2 bytes(16 bits) file. 
-The encoder saves the file as .lzw with the filename similar to the file provided for encoding.

Decoder:-
-The decoder also uses the scanner class to get inputs from the user as a path to the encoded file.
-Decoder implements the standard charset UTF-16BE while reading the file . 
-The underlying algorithm is derived from the pseudocode provided as an example.
-The decoder saves the file as .txt file with the filename similar to file being decoded and appending _decoded to the file.


==HOW TO RUN===

-The scanner will need the full path of the file location.
-The output file will be saved in the source code destination.
