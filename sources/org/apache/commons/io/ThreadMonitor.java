package org.apache.commons.io;

import java.time.Duration;

class ThreadMonitor implements Runnable {
    private final Thread thread;
    private final Duration timeout;

    static Thread start(Duration duration) {
        return start(Thread.currentThread(), duration);
    }

    static Thread start(Thread thread2, Duration duration) {
        if (duration.isZero() || duration.isNegative()) {
            return null;
        }
        Thread thread3 = new Thread(new ThreadMonitor(thread2, duration), "ThreadMonitor");
        thread3.setDaemon(true);
        thread3.start();
        return thread3;
    }

    static void stop(Thread thread2) {
        if (thread2 != null) {
            thread2.interrupt();
        }
    }

    private ThreadMonitor(Thread thread2, Duration duration) {
        this.thread = thread2;
        this.timeout = duration;
    }

    public void run() {
        try {
            sleep(this.timeout);
            this.thread.interrupt();
        } catch (InterruptedException unused) {
        }
    }

    private static void sleep(Duration duration) throws InterruptedException {
        long millis = duration.toMillis();
        long currentTimeMillis = System.currentTimeMillis() + millis;
        do {
            Thread.sleep(millis);
            millis = currentTimeMillis - System.currentTimeMillis();
        } while (millis > 0);
    }
}
