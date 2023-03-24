package threads;

import java.util.Random;

public class CounterNoThreads {
    private String name;

    public CounterNoThreads(String name) {
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

    public static void main(String[] args) {
        String [] names = {"A", "B", "C", "D", "E"};

        long startTime = System.nanoTime();
        for(String name : names) {
            CounterNoThreads counter = new CounterNoThreads(name);
            counter.count();
        }
        long endTime = System.nanoTime();
        System.out.println("Time: " + (endTime - startTime));
        System.out.println("DONE!");
    }
}
