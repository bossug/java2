package com.company;

public class MyArraySizeException extends Throwable {
    public MyArraySizeException(String mess) {
        System.out.printf("Размер массива %s не корректный!",mess);
        System.out.println();
    }
}
