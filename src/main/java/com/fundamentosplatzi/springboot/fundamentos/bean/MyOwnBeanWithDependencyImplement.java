package com.fundamentosplatzi.springboot.fundamentos.bean;

import java.util.List;

public class MyOwnBeanWithDependencyImplement implements MyOwnBeanWithDependency{

    private MyOwnOperation myOwnOperation;

    public MyOwnBeanWithDependencyImplement(MyOwnOperation myOwnOperation){
        this.myOwnOperation= myOwnOperation;
    }

    @Override
    public void displayElements() {
        System.out.println("it call to MyOwnOperationImplement by generate random list");
        List<Integer> randNums = this.myOwnOperation.generateRandomElements(500, 10);

        randNums.forEach(System.out::println);

        System.out.println("Hi from my implementation of a bean with dependency wearing Streams and Lists");
    }
}
