package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainCollection {

    public static void main(String[] args) {
        ListingText();
        BookPhone();
    }

    public static void ListingText(){
        String[] fruit = {
                "яблоко",
                "апельсин",
                "груша",
                "яблоко",
                "киви",
                "киви",
                "банан",
                "киви",
                "лимон",
                "манго",
                "банан",
                "киви",
                "лимон",
                "манго",
                "арбуз"
        };
        HashMap<String, Integer> textUnic = new HashMap<>();
        for (String x : fruit) {
            Integer c = textUnic.get(x);
            textUnic.put(x, c == null ? 1 : c+1);
            if(c!=null && c > 0){
                textUnic.remove(x);
            }
        }
        System.out.println("Уникальные не повторяющиеся:");
        System.out.println(textUnic);

        HashMap<String, Integer> text = new HashMap<>();
        for (String x : fruit) {
            Integer c = text.get(x);
            text.put(x, c == null ? 1 : c+1);
        }
        System.out.println("Все с повторами:");
        System.out.println(text);
    }
    public static void BookPhone(){
        Phonebook book = new Phonebook();
        book.addContact("Рома", "89185662356");
        book.addContact("Рита", "89895652233");
        book.addContact("Коля", "89899856655");
        book.addContact("Рома", "89178745566");
        book.addContact("Лиза", "89786452255");
        book.addContact("Лиза", "89458956655");

        System.out.println("Телефонный справочник:");

        book.findPrint("Рома");
        book.findPrint("Лиза");
        book.findPrint("Коля");
    }
}
