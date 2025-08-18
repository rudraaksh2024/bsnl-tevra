package org.apache.xmlbeans.impl.common;

public class Mutex {
    private int lock_count = 0;
    private Thread owner = null;

    public synchronized void acquire() throws InterruptedException {
        while (!tryToAcquire()) {
            wait();
        }
    }

    public synchronized boolean tryToAcquire() {
        Thread thread = this.owner;
        if (thread == null) {
            this.owner = Thread.currentThread();
            this.lock_count = 1;
            return true;
        } else if (thread != Thread.currentThread()) {
            return false;
        } else {
            this.lock_count++;
            return true;
        }
    }

    public synchronized void release() {
        if (this.owner == Thread.currentThread()) {
            int i = this.lock_count - 1;
            this.lock_count = i;
            if (i <= 0) {
                this.owner = null;
                notify();
            }
        } else {
            throw new IllegalStateException("Thread calling release() doesn't own mutex");
        }
    }
}
