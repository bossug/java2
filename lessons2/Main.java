package com.company;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Main {

    public static void main(String[] args) {
        run();
    }
    public static void run(){
        int result = 0;
        String[][]  mass1 ={
                {"1","1","3","4"},
                {"1","2","аа3","4"},
                {"1","1","3","4"},
                {"1","1","3","4"},
        };
        String[][]  mass2 = {
                {"1","1","3","4"},
                {"1","2","5","4","9"},
                {"1","1","3","4"},
                {"1","1","3","4"},
        };
        String[][]  mass3 = {
                {"1","1","3","4"},
                {"1","2","5","4"},
                {"1","1","3","4"},
                {"1","1","3","4"},
        };
        System.out.println("------------------");
        try {
            result = 0;
            result = analizer(mass1,1);
        } catch (MyArraySizeException e){
        } catch (MyArrayDataException e){
        } finally {
            System.out.println("Результат суммирования элементов массива mass1 равна "+String.valueOf(result));
        }
        System.out.println("------------------");
        try {
            result = 0;
            result = analizer(mass2,2);
        } catch(MyArraySizeException e){
        } catch(MyArrayDataException e){
        } finally {
            System.out.println("Результат суммирования элементов массива mass2 равна "+String.valueOf(result));
        }
        System.out.println("------------------");
        try {
            result = 0;
            result = analizer(mass3,3);
        } catch(MyArraySizeException e){
        } catch(MyArrayDataException e){
        } finally {
            System.out.println("Результат суммирования элементов массива mass3 равна "+String.valueOf(result));
        }
    }
    public static int analizer(String[][] array, int p) throws MyArraySizeException, MyArrayDataException {
        int summ = 0;
        int row = 0;
        int cell = 0;
        String nameMass = "mass" + String.valueOf(p);
        if(array.length > 4) throw new MyArraySizeException(nameMass);
        for(int i = 0; i < array.length; i++){
            if(array[i].length > 4) throw new MyArraySizeException(nameMass);
            row = i+1;
            for(int c = 0; c < array[i].length; c++){
                cell = c+1;
                try{
                    summ += parseInt(array[i][c]);
                } catch (IllegalArgumentException e){
                    String message = nameMass+ " в " + String.valueOf(row) + " ряду, " + String.valueOf(cell) + " ячейке";
                    summ += 0;
                    throw new MyArrayDataException(message);
                }
            }
        }
        return summ;
    }
}
