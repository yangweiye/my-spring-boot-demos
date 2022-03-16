package com.yangweiye.springbootdemos.single;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator3 {
    private AtomicLong atomicLong = new AtomicLong();
    private static IdGenerator3 instance = null;

    public IdGenerator3() {
    }

    public static final IdGenerator3 getInstance() {
        if (instance == null) {
            synchronized (IdGenerator3.class) {
                if (instance == null) {
                    instance = new IdGenerator3();
                }
            }
        }

        return instance;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
