package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("We have entered to the metod printwithDependency");
        int numero = 1;
        LOGGER.debug("The number send with parameter to dependencey operation is: " + numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hi from the implementation of a bean with dependency");
    }
}
