package dev.app.rentingCar_boot.utils;

import dev.app.rentingCar_boot.model.Booking;
import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.Client;
import dev.app.rentingCar_boot.repository.BookingRepository;
import dev.app.rentingCar_boot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PopulateAllTables {

    @Autowired
    private PopulateCar populateCar;

    @Autowired
    private PopulateClient populateClient;

    @Autowired
    private PopulateBooking populateBooking;

    @Autowired
    private PopulateDrivingCourse populateDrivingCourse;

    public String populateAllTables(int qty) {

        // let s populate cars first
        PopulateStatus populateCarStatus = populateCar.populateCar(qty);
        System.out.println("\nPopulate Car operations: " + populateCarStatus.getQty() +
                " \n" + populateCarStatus.getMessage());

        // let s populate clients
        PopulateStatus populateClientStatus = null;
        if (populateCarStatus.isStatus()) {
        populateClientStatus = populateClient.populateClient(qty);
        System.out.println("\nPopulate Client operations: " + populateClientStatus.getQty() +
                " \n" + populateClientStatus.getMessage());
        } else return "Populate Car operations failed";


        // once cars are populated, let s populate bookings
        PopulateStatus populateBookingStatus = null;
        if (populateClientStatus.isStatus()) {
        populateBookingStatus = populateBooking.populateBooking(qty);
        System.out.println("\nPopulate Booking operations: " + populateBookingStatus.getQty() +
                " \n" + populateBookingStatus.getMessage());
        } else return "Populate Client operations failed";

        // once bookings are populated, let s populate driving courses
        PopulateStatus populateDrivingCourseStatus = null;
        if (populateBookingStatus.isStatus()){
        populateDrivingCourseStatus = populateDrivingCourse.populateDrivingCourse(qty);
        System.out.println("\nPopulate DrivingCourse operations: " + populateDrivingCourseStatus.getQty() +
                " \n" + populateDrivingCourseStatus.getMessage());
        } else return "Populate Booking operations failed";

        if (!populateDrivingCourseStatus.isStatus()) return "Populate DrivingCourse operations failed";

    return "Populate All Tables operations completed successfully";
    }

}
