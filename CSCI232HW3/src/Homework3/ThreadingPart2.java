package Homework3;
import java.util.Random;
/**
 * Bowen Kruse
 * 12/20/2019
 * Part 2 of Homework 3 CSCI 232
 * Threads share an array to input random integers
 */

public class ThreadingPart2 {
    public static void main(String[] args) {

        // Create the result set containing the result
        ResultSet Set = new ResultSet(10);
        Thread[] threads = new Thread[Set.getSize()];

        // Create threads
        for (int i = 0; i < Set.getSize(); i++) {
            threads[i] = new Thread(new Task(
                    Set.createResultSetter(i)));
        }

        // Start threads
        for (int i = 0; i < Set.getSize(); i++) {
            threads[i].start();
        }

        // Wait until threads complete aka sharing
        for (int i = 0; i < Set.getSize(); i++) {
            try {
                threads[i].join(); //.join() function key to the sharing nature of this program
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the result as assigned by Program Assignment 2 CSCI 232
        for (int i = 0; i < Set.getSize(); i++) {

            System.out.println("Thread " + i + " inserted " +  Set.getResult(i));
        }
    }


    //Interface for the result set up
    public static interface ResultSetter {
        public void setResult(int result);
    }


    //Results container
    public static class ResultSet {
        private final int[] results;

        public ResultSet(int size) {
            results = new int[size];
        }

        public int getSize() {
            return results.length;
        }

        public ResultSetter createResultSetter(final int position) {
            return new ResultSetter() {
                public void setResult(int result) {
                    ResultSet.this.setResult(position, result);
                }
            };
        }

        //getter setter as you recommended
        public int getResult(int position) {
            return results[position];
        }

        public void setResult(int position, int result) {
            results[position] = result;
        }
    }

    // The thread .Run()
    public static class Task implements Runnable {
        private ResultSetter resultSetter;
        Random rand = new Random();

        public Task(ResultSetter resultSetter) {
            this.resultSetter = resultSetter;
        }

        public void run() {
            resultSetter.setResult(rand.nextInt(99));
        }
    }
}

