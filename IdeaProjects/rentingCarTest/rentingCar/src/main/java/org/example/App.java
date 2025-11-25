package org.example;

import org.example.managers.CarManagement;

public class App
{
    public static void main (String [] args ) {
        System.out.println("Starting code");

        //this is a static method
        // it can be called without creating an instance of the class

        //RentingCarTests.testCar();
        //RentingCarTests.testBooking();

        //RentingCarTests.testCar();
        //RentingCarTest.TestFaker();

        //RentingCarTests.createFakeCarListTest();

        //RentingCarTest.myDataStoreTest();

        DataStore myDataStore = new DataStore();
        myDataStore.setId (1);
        myDataStore.setLabel("Renting Card Fake DB v1.0");
        long epoch = System.currentTimeMillis();//1000;
        myDataStore.setCreationDate(epoch);
        myDataStore.setLastModification(epoch);
        myDataStore.setActive(true);

        FakeDataDBPopulator.populateDBByCars(myDataStore);

        CarManagement.PrintCarList(myDataStore.getCars());

        System.out.println("Finished");6
    }




}
