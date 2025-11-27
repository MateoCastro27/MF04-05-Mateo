package dev.app.rentingcartestvaadin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PopulateAtStartup implements CommandLineRunner {

    @Autowired
    private PopulateAllTables populateAllTables;

    public void run(String... args) throws Exception {
        System.out.println("Starting database population...");


        int qty = 9;
        String result = populateAllTables.populateAllTables(qty);
        System.out.println(result);

        System.out.println(" Database population completed.");
    }
}
