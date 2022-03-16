package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.questions.CQueue;
import com.yangweiye.springbootdemos.single.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SingleTests {
    @Test
    public void incorrectId() {
        new Thread(() -> {
            IdGenerator idGenerator = new IdGenerator();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();

        new Thread(() -> {
            IdGenerator idGenerator = new IdGenerator();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();

        new Thread(() -> {
            IdGenerator idGenerator = new IdGenerator();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();
    }

    @Test
    public void correctId() {
        new Thread(() -> {
            IdGenerator idGenerator = IdGenerator.getInstance();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();

        new Thread(() -> {
            IdGenerator idGenerator = IdGenerator.getInstance();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();

        new Thread(() -> {
            IdGenerator idGenerator = IdGenerator.getInstance();
            for (int i = 0; i < 100; i++) {
                System.out.println(idGenerator.getId());
            }
        }).start();
    }

    @Test
    public void test09() {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
}
