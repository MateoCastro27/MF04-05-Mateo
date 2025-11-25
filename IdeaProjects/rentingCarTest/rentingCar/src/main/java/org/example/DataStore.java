package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static String id;
    private static List<Car> cars = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList();
    private static  boolean isActive;
    private static int creationDate;


    public void setId(int i) {

    }

    public void setLabel(String s) {
    }

    public void setCreationDate(long epoch) {
    }

    public void setLastModification(long epoch) {
    }


    public void setActive(boolean b) {
    }

    public List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> cars) {
        DataStore.cars = cars;
    }
}



