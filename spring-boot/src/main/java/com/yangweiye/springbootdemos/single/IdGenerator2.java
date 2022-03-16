package com.yangweiye.springbootdemos.single;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator2 {
    private AtomicLong atomicLong = new AtomicLong();
    private static IdGenerator2 instance = null;

    public IdGenerator2() {
    }

    public static final synchronized IdGenerator2 getInstance() {
        if (null == instance) {
            instance = new IdGenerator2();
        }
        return instance;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
