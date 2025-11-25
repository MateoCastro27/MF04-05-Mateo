package org.example.managers;

import org.example.Car;
import org.example.DataStore;

import java.util.List;

public class CarManagement {

    public static void PrintCarList(List<Car> cars) {
        int index = 1;
        for (Car car : cars)
        {
            System.out.printf("\t" + index + ". " + car);
            index++;
        }

        System.out.printf("\n");
    }
}
