package com.yangweiye.springbootdemos.pojo;

public class StaticProxyAnimal implements Animal {
    private Animal animal;

    public StaticProxyAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void call() {
        System.out.println("我饿了");
        animal.call();
    }
}
