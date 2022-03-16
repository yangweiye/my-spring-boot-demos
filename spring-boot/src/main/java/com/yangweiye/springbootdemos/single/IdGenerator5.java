package com.yangweiye.springbootdemos.single;

import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator5 {
    INSTANCE;
    private AtomicLong atomicLong = new AtomicLong();

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}

