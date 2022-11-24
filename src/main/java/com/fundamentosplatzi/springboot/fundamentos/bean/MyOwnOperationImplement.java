package com.fundamentosplatzi.springboot.fundamentos.bean;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MyOwnOperationImplement implements MyOwnOperation{
    @Override
    public List<Integer> generateRandomElements(int maxElement, int maxLimit) {
        return new Random().ints(0, maxElement)
                .limit(maxLimit)
                .boxed() // Retorna un IntStream
                .collect(Collectors.toList());
    }
}
