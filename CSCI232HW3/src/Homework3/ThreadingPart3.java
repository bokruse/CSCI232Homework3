package Homework3;
import java.util.Scanner;
import java.io.*;
/**
 * Bowen Kruse
 * 12/20/2019
 * Part 3 of Homework 3 CSCI 232
 * Takes lines of both input.txt and input2.txt
 * and compares each line. If the lines are not
 * identical the lines will be output to diff.txt
 */
public class ThreadingPart3 {
    public static void main (String args[])  throws IOException {

        //Opening of two separate input files using scanner
        Scanner scan1 = new Scanner(new File("src/input.txt"));
        Scanner scan2 = new Scanner(new File("src/input2.txt"));

        PrintWriter diffLines = new PrintWriter(new FileWriter("diff.txt"));

        while (scan1.hasNext() && scan2.hasNext()) {
            String line1 = scan1.nextLine();
            String line2 = scan2.nextLine();

            //If the line of the input file does not match the other, it will be printed into diff.txt
            if (!line2.equals(line1)) {
                diffLines.println(line1);
                diffLines.println(line2);
            }
        }
        diffLines.close();
    }
}

