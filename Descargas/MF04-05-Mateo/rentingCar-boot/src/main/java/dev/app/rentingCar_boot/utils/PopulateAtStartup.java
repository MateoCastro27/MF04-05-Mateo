package dev.app.rentingCar_boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PopulateAtStartup implements CommandLineRunner {

    @Autowired
    private PopulateAllTables populateAllTables;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting database population...");

        // Aquí definimos cuantos registros queremos
        int qty = 5;  // por ejemplo: 5 coches, 5 clientes, 5 bookings, etc.

        String result = populateAllTables.populateAllTables(qty);
        System.out.println(result);

        System.out.println("✅ Database population completed.");
    }
}
