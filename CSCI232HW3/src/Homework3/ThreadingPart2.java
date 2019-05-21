package Homework3;
import java.util.Random;
/**
 * Bowen Kruse
 * 12/20/2019
 * Part 2 of Homework 3 CSCI 232
 * Four Threads share an array to input random integers
 */

public class ThreadingPart2 {
    public static void main(String[] args) {

        // Create the result set containing the result
        ResultSet resultSet = new ResultSet(10);
        Thread[] threads = new Thread[resultSet.getSize()];

        // Create threads
        for (int i = 0; i < resultSet.getSize(); i++) {
            threads[i] = new Thread(new TTask(
                    resultSet.createResultSetter(i)));
        }

        // Start threads
        for (int i = 0; i < resultSet.getSize(); i++) {
            threads[i].start();
        }

        // Wait until threads complete aka sharing
        for (int i = 0; i < resultSet.getSize(); i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the result
        for (int i = 0; i < resultSet.getSize(); i++) {

            System.out.println(threads[i] + " inserted " +  resultSet.getResult(i));
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
        public synchronized int getResult(int position) {
            return results[position];
        }

        public synchronized void setResult(int position, int result) {
            results[position] = result;
        }
    }

    //
    public static class TTask implements Runnable {
        private ResultSetter resultSetter;
        Random rand = new Random();

        public TTask(ResultSetter resultSetter) {
            this.resultSetter = resultSetter;
        }

        public void run() {
            resultSetter.setResult(rand.nextInt(99));
        }
    }
}

