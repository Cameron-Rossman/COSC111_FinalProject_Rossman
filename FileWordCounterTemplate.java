/*
 * Lab 9: Exception Handling and File I/O
 * ------------------------------------
 * In this lab, you will read a text file, count the number of words,
 * and handle any errors that might occur using try/catch.
 */
import java.util.Scanner;
import java.io.*;

public class FileWordCounterTemplate {
    public static void main(String[] args) {

        // TODO 1: Use a try/catch block to handle file reading. 
        // Get the file name from the user as input from the keyboard
        // - Create a File object for user-specified file (I will test with input.txt)
        // - Create a Scanner to read from that file
        // - Count the total number of words
        // - Print the total count
        
        // - Print a friendly error message if the file is not found
        Scanner kb=new Scanner(System.in);

        // - ask user for file name
        System.out.println("What is the file name");

        String fileName=kb.nextLine();

        // counters for words, lines, and characters
        int wordCount=0;
        int charCount=0;
        int lineCount=0;

        // read the file and count
        try{
            File f=new File(fileName);
            Scanner fileScanner=new Scanner(f);

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();

                // count characters and lines
                lineCount++;
                charCount += line.length();

                // count words inside the line
                Scanner lineScanner=new Scanner(line);
                while (lineScanner.hasNext()) {
                    lineScanner.next();
                    wordCount++;
                }
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }


        

        // TODO 2: Add another try/catch block below to WRITE output to a new file.
        // Use PrintWriter to write "Total words: X" to a file called "output.txt".

        // write results to output.txt
        try{
            PrintWriter writer=new PrintWriter("output.txt");
            writer.println("Total words: " + wordCount);

            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error writing to output file");
        }

        
        
        // display totals
        System.out.println("Total Words: " + wordCount);
        System.out.println("Total Lines: " + lineCount);
        System.out.println("Total Characters: " + charCount);

        // close scanner
        kb.close();
 
    }
}
