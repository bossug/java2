package com.company;

public class MyArrayDataException extends Throwable {
    public MyArrayDataException(String message) {
        System.out.println("некорректный массив " +message);
    }
}
