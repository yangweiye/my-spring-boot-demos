package com.yangweiye.springbootdemos.single;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator4 {
    private AtomicLong atomicLong = new AtomicLong();

    private static final class SingleHandler {
        private static final IdGenerator4 singleHandler = new IdGenerator4();
    }

    public IdGenerator4() {
    }

    public static final IdGenerator4 getInstance() {
        return SingleHandler.singleHandler;
    }

    public Long getId() {
        return atomicLong.incrementAndGet();
    }
}
