package com.yangweiye.springbootdemos.single;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private AtomicLong atomicLong = new AtomicLong();
    private static final IdGenerator instance = new IdGenerator();

    public IdGenerator() {
    }

    public static final IdGenerator getInstance() {
        return instance;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
