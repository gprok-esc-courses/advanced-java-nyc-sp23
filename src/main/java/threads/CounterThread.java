package threads;

import java.util.ArrayList;
import java.util.Random;

public class CounterThread extends Thread {
    private String name;

    public CounterThread(String name) {
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

    public static void main(String[] args) {
        String [] names = {"A", "B", "C", "D", "E"};
        ArrayList<CounterThread> threads = new ArrayList<>();

        long startTime = System.nanoTime();
        for(String name : names) {
            CounterThread counter = new CounterThread(name);
            threads.add(counter);
            counter.start();
        }

        // Wait for threads to finish
        for(CounterThread th : threads) {
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
