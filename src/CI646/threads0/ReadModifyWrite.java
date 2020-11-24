package CI646.threads0;

import java.util.concurrent.atomic.AtomicInteger;

// Example adapted from https://www.javacodegeeks.com

public class ReadModifyWrite {
    private AtomicInteger number = new AtomicInteger();

    public void incrementNumber() {
        number.getAndIncrement();
        Utils.simulateInterrupt(1);
    }

    public int getNumber() {
        return this.number.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadModifyWrite rmw = new ReadModifyWrite();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> rmw.incrementNumber()).start();
        }

        Thread.sleep(5000);
        System.out.println("Final number (should be 100): " + rmw.getNumber());
    }
}
