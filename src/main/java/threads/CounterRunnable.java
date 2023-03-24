package threads;

import java.util.ArrayList;
import java.util.Random;

public class CounterRunnable implements Runnable {

    private String name;
    private Thread thread;

    public CounterRunnable(String name) {
        this.name = name;
    }

    public void count() {
        Random rnd = new Random();
        for(int i = 1; i <= 10; i++) {
            System.out.println(name + ". " + i);
            try {
                Thread.sleep(rnd.nextInt(3000));
            } catch (InterruptedException e) {
                System.out.println("Sleep failed");
            }
        }
    }

    @Override
    public void run() {
        count();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void join() throws InterruptedException {
        thread.join();
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E"};
        ArrayList<CounterRunnable> threads = new ArrayList<>();

        long startTime = System.nanoTime();
        for (String name : names) {
            CounterRunnable counter = new CounterRunnable(name);
            threads.add(counter);
            counter.start();
        }

        // Wait for threads to finish
        for (CounterRunnable th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                System.out.println("Join failed");
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime));
        System.out.println("DONE!");
    }
}
