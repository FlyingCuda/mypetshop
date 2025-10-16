package com.example.mypetshop.demo;

public class JPrinter {
    private static final KPrinter helper = new KPrinter();

    public void print(String language) {
        System.out.println(this.getClass().getName() + " is printing from " + language);
    }

    public void printWithHelper() {
        helper.print("Java");
    }
}
