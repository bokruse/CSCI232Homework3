package Homework3;
/**
 * Bowen Kruse
 * 12/20/2019
 * Part 1 of Homework 3 CSCI 232
 * Uses two threads two create two timers that counts
 * one and three second intervals
 */

public class ThreadingPart1 implements Runnable {

    public void run() {
        class Timer1 extends Thread {
            public void run() {
                int i = 1;
                try {
                    while (i > 0) {
                        Thread.sleep(1000);
                        System.out.println("Seconds passed: " + i);
                        i++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Timer3 extends Thread {
            public void run() {
                int j = 1;
                try {
                    while (j > 0) {
                        Thread.sleep(3000);
                        System.out.println("\tSets of 3 seconds passed: " + j);
                        j++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    Timer1 t1 = new Timer1();
        t1.start();
    Timer3 t3 = new Timer3();
        t3.start();
    }

    public static void main(String[] args) { (new ThreadingPart1()).run(); }
}


