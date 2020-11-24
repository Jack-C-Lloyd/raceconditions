package CI646.threads0;

import java.util.concurrent.atomic.AtomicInteger;

// Example adapted from https://www.javacodegeeks.com

public class CheckThenAct {
    private AtomicInteger number = new AtomicInteger();

    public void changeNumber() {
        if (number.compareAndSet(0, -1)) {
            Utils.simulateInterrupt(5);
            System.out.println(Thread.currentThread().getName() + " | Changed");
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
    }

    public static void main(String[] args) {
        final CheckThenAct checkAct = new CheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkAct.changeNumber();
                }
            }, "T" + i).start();
        }
    }
}