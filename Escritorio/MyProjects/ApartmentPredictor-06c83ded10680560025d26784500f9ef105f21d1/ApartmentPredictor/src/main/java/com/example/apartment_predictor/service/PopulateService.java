package com.example.apartment_predictor.service;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateService {

    @Autowired private OwnerRepository ownerRepository;
    @Autowired private ReviewerRepository reviewerRepository;
    @Autowired private SchoolRepository schoolRepository;
    @Autowired private ApartmentRepository apartmentRepository;
    @Autowired private ReviewRepository reviewRepository;

    public void populateAll() {
        System.out.println("\n===== POBLADO MANUAL INICIADO =====");

        populateOwners();
        populateReviewers();
        populateSchools();
        populateApartments();
        populateReviews();

        System.out.println("===== POBLADO MANUAL FINALIZADO =====");
    }

    private void populateOwners() {
        if (ownerRepository.count() > 0) return;
        Owner o1 = new Owner(); o1.setName("Juan Pérez"); o1.setEmail("juan@ej.com"); o1.setPhone("600111222"); o1.setAge(45);
        Owner o2 = new Owner(); o2.setName("María López"); o2.setEmail("maria@ej.com"); o2.setPhone("600333444"); o2.setAge(38);
        ownerRepository.saveAll(List.of(o1, o2));
    }

    private void populateReviewers() {
        if (reviewerRepository.count() > 0) return;
        Reviewer r1 = new Reviewer(); r1.setName("Ana Gómez"); r1.setEmail("ana@ej.com"); r1.setAge(32);
        Reviewer r2 = new Reviewer(); r2.setName("Carlos Ruiz"); r2.setEmail("carlos@ej.com"); r2.setAge(29);
        reviewerRepository.saveAll(List.of(r1, r2));
    }

    private void populateSchools() {
        if (schoolRepository.count() > 0) return;
        School s1 = new School(); s1.setName("Colegio San José"); s1.setAddress("Calle Mayor 45"); s1.setType("Público"); s1.setLatitude(40.4168); s1.setLongitude(-3.7038);
        School s2 = new School(); s2.setName("Instituto Einstein"); s2.setAddress("Av. Libertad 12"); s2.setType("Privado"); s2.setLatitude(40.4300); s2.setLongitude(-3.7100);
        schoolRepository.saveAll(List.of(s1, s2));
    }

    private void populateApartments() {
        List<Owner> owners = ownerRepository.findAll();
        List<School> schools = schoolRepository.findAll();
        if (owners.isEmpty() || schools.isEmpty()) return;

        Apartment apt1 = new Apartment(13300000L, 7420, 4, 2, 3, "yes", "no", "no", "no", "yes", 2, "yes", "furnished");
        apt1.setOwner(owners.get(0));
        apt1.setNearbySchools(new ArrayList<>(schools.subList(0, Math.min(2, schools.size()))));

        Apartment apt2 = new Apartment(8500000L, 5200, 3, 2, 2, "yes", "yes", "no", "yes", "yes", 1, "no", "semi-furnished");
        apt2.setOwner(owners.get(owners.size() > 1 ? 1 : 0));
        apt2.setNearbySchools(new ArrayList<>(List.of(schools.get(0))));

        apartmentRepository.saveAll(List.of(apt1, apt2));
    }

    private void populateReviews() {
        List<Apartment> apartments = (List<Apartment>) apartmentRepository.findAll();  // ← quitamos el cast innecesario
        List<Reviewer> reviewers = reviewerRepository.findAll();
        if (apartments.isEmpty() || reviewers.isEmpty()) return;

        Review r1 = new Review();
        r1.setTitle("Muy recomendado");
        r1.setContent("Ubicación perfecta y muy limpio");
        r1.setRating(5);
        r1.setReviewDate(LocalDate.now());
        r1.setApartment(apartments.get(0));
        r1.setReviewer(reviewers.get(0));

        Review r2 = new Review();
        r2.setTitle("Buena relación calidad-precio");
        r2.setContent("Todo correcto, recomendado");
        r2.setRating(4);
        r2.setReviewDate(LocalDate.now());
        r2.setApartment(apartments.get(apartments.size() > 1 ? 1 : 0));
        r2.setReviewer(reviewers.get(reviewers.size() > 1 ? 1 : 0));

        reviewRepository.saveAll(List.of(r1, r2));
    }
}