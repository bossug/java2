package com.company;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    public static void main(String[] args) {
        float[] arr = new float[size];

        listArray(arr);
        long a1 = System.currentTimeMillis();

        listArrayMethod1(arr,1);
        System.out.println("Время выполнения метод 1: " + (System.currentTimeMillis() - a1));

        long a2 = System.currentTimeMillis();
        listArrayMethod2(arr,2);

        System.out.println("Время выполнения метод 2 " + (System.currentTimeMillis() - a2));
    }

    public static void listArray(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }
    public static void listArrayMethod1(float[] arr, int a){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

    }

    public static void listArrayMethod2(float[] arrAll, int a){
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arrAll, 0, arr1, 0, h);
        System.arraycopy(arrAll, h, arr2, 0, h);

        new Thread(new Runnable() {
            @Override
            public void run() {
                listArrayMethod1(arr1,0);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                listArrayMethod1(arr2,h);
            }
        }).start();

        System.arraycopy(arr1, 0, arrAll, 0, h);
        System.arraycopy(arr2, 0, arrAll, h, h);
    }
}
