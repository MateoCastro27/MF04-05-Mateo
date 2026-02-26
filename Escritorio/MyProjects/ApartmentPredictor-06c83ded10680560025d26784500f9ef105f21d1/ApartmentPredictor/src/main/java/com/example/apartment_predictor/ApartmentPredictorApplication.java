package com.example.apartment_predictor;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApartmentPredictorApplication implements CommandLineRunner {

    @Autowired private ApartmentRepository apartmentRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private OwnerRepository ownerRepository;
    @Autowired private ReviewerRepository reviewerRepository;
    @Autowired private SchoolRepository schoolRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApartmentPredictorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n===== INICIANDO POBLADO COMPLETO PARA LA PRÁCTICA =====");

        // 1. Poblar entidades base (necesarias para enlazar)
        populateOwners();
        populateReviewers();
        populateSchools();

        // 2. Tus métodos originales (ahora con datos previos)
        testApartmentsInsert();
        testReviewsInsert();

        System.out.println("\n===== POBLADO FINALIZADO - TODAS LAS TABLAS DEBEN ESTAR POBLADAS =====");
        System.out.println("Abre ahora: http://localhost:8080/h2-console");
        System.out.println("JDBC URL: jdbc:h2:file:./db/apartmentdb   User: sa   Password: vacío");
    }

    // POBLAR OWNERS (dueños → heredan de Person)
    private void populateOwners() {
        if (ownerRepository.count() > 0) {
            System.out.println("Owners ya existen, saltando...");
            return;
        }

        System.out.println("Creando dueños...");
        Owner o1 = new Owner();
        o1.setName("Juan Pérez García");
        o1.setEmail("juan.perez@propiedades.es");
        o1.setPhone("600 111 222");
        o1.setAge(45);

        Owner o2 = new Owner();
        o2.setName("María López Sánchez");
        o2.setEmail("maria.lopez@propiedades.es");
        o2.setPhone("600 333 444");
        o2.setAge(38);

        ownerRepository.saveAll(List.of(o1, o2));
        System.out.println("Creados " + ownerRepository.count() + " dueños.");
    }

    // POBLAR REVIEWERS (revisores → heredan de Person)
    private void populateReviewers() {
        if (reviewerRepository.count() > 0) {
            System.out.println("Reviewers ya existen, saltando...");
            return;
        }

        System.out.println("Creando revisores...");
        Reviewer r1 = new Reviewer();
        r1.setName("Ana Gómez Martínez");
        r1.setEmail("ana.gomez@usuarios.es");
        r1.setAge(32);

        Reviewer r2 = new Reviewer();
        r2.setName("Carlos Ruiz Fernández");
        r2.setEmail("carlos.ruiz@usuarios.es");
        r2.setAge(29);

        reviewerRepository.saveAll(List.of(r1, r2));
        System.out.println("Creados " + reviewerRepository.count() + " revisores.");
    }

    // POBLAR SCHOOLS
    private void populateSchools() {
        if (schoolRepository.count() > 0) {
            System.out.println("Schools ya existen, saltando...");
            return;
        }

        System.out.println("Creando escuelas...");
        School s1 = new School();
        s1.setName("Colegio Público Cervantes");
        s1.setAddress("C/ Alcalá 123, Madrid");
        s1.setType("Público");
        s1.setLatitude(40.4168);
        s1.setLongitude(-3.7038);

        School s2 = new School();
        s2.setName("Instituto Privado Galileo");
        s2.setAddress("Av. Europa 45, Madrid");
        s2.setType("Privado");
        s2.setLatitude(40.4500);
        s2.setLongitude(-3.6900);

        schoolRepository.saveAll(List.of(s1, s2));
        System.out.println("Creados " + schoolRepository.count() + " escuelas.");
    }

    // TUS MÉTODOS ORIGINALES (con enlaces y tipos explícitos)
    public void testApartmentsInsert() {
        System.out.println("Creando y guardando apartamentos...");

        List<Owner> owners = ownerRepository.findAll();
        List<School> schools = schoolRepository.findAll();

        if (owners.isEmpty() || schools.isEmpty()) {
            System.out.println("ERROR: Faltan dueños o escuelas.");
            return;
        }

        Apartment apartment1 = new Apartment(
                13300000L, 7420, 4, 2, 3,
                "yes", "no", "no", "no", "yes",
                2, "yes", "furnished"
        );
        apartment1.setOwner(owners.get(0));

        List<School> nearby1 = new ArrayList<>();
        nearby1.add(schools.get(0));
        if (schools.size() > 1) nearby1.add(schools.get(1));
        apartment1.setNearbySchools(nearby1);

        Apartment apartment2 = new Apartment(
                8500000L, 5200, 3, 2, 2,
                "yes", "yes", "no", "yes", "yes",
                1, "no", "semi-furnished"
        );
        apartment2.setOwner(owners.get(owners.size() > 1 ? 1 : 0));

        List<School> nearby2 = new ArrayList<>();
        nearby2.add(schools.get(0));
        apartment2.setNearbySchools(nearby2);

        Apartment apartment3 = new Apartment(
                6200000L, 3800, 2, 1, 1,
                "no", "no", "yes", "yes", "no",
                0, "yes", "unfurnished"
        );
        apartment3.setOwner(owners.get(0));

        List<School> nearby3 = new ArrayList<>();
        nearby3.add(schools.get(schools.size() - 1));
        apartment3.setNearbySchools(nearby3);

        apartmentRepository.saveAll(List.of(apartment1, apartment2, apartment3));

        System.out.println("\n=== Apartamentos en la base de datos ===");
        int index = 0;
        for (Apartment a : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index + " -> " + a);
        }
    }

    public void testReviewsInsert() {
        System.out.println("Creando y guardando reseñas...");

        List<Apartment> apartments = (List<Apartment>) apartmentRepository.findAll();
        List<Reviewer> reviewers = reviewerRepository.findAll();

        if (apartments.isEmpty() || reviewers.isEmpty()) {
            System.out.println("ERROR: Faltan apartamentos o revisores.");
            return;
        }

        Review review1 = new Review();
        review1.setTitle("Amazing ap        artment with great location");
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended!");
        review1.setRating(5);
        review1.setReviewDate(LocalDate.of(2024, 1, 15));
        review1.setApartment(apartments.get(0));
        review1.setReviewer(reviewers.get(0));

        Review review2 = new Review();
        review2.setTitle("Good value for money");
        review2.setContent("Nice apartment overall. Clean and well-maintained. The only downside is the parking situation but everything else was great.");
        review2.setRating(4);
        review2.setReviewDate(LocalDate.of(2024, 2, 3));
        review2.setApartment(apartments.get(1));
        review2.setReviewer(reviewers.get(reviewers.size() > 1 ? 1 : 0));

        reviewRepository.saveAll(List.of(review1, review2));

        System.out.println("\n=== Reseñas en la base de datos ===");
        int index = 0;
        for (Review r : reviewRepository.findAll()) {
            index++;
            System.out.println("#" + index + " -> " + r);
        }
    }
}