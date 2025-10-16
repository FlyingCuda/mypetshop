package com.example.mypetshop;

import com.example.mypetshop.demo.JPrinter;
import com.example.mypetshop.demo.KPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPetShopApplication {
    private static final JPrinter jPrinter = new JPrinter();
    private static final KPrinter kPrinter = new KPrinter();

	public static void main(String[] args) {
        jPrinter.print("Java");
        kPrinter.print("Java");
        jPrinter.printWithHelper();
        kPrinter.printWithHelper();
		SpringApplication.run(MyPetShopApplication.class, args);
	}

}
