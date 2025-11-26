package dev.app.rentingcartestvaadin.controller;

import dev.app.rentingcartestvaadin.model.Booking;
import dev.app.rentingcartestvaadin.repository.BookingRepository;
import com.vaadin.hilla.Endpoint;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Endpoint
@AnonymousAllowed // permite que el frontend llame al spring boot sin login
public class BookingEndpoint {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return (List<Booking>) bookingRepository.findAll();
    }
}
