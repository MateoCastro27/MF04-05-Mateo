package com.example.apartment_predictor.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String type; // e.g., "public", "private"
    private Double latitude;
    private Double longitude;

    // Relación inversa (many-to-many)
    @ManyToMany(mappedBy = "nearbySchools")
    private List<Apartment> nearbyApartments = new ArrayList<>();

    public School() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public List<Apartment> getNearbyApartments() { return nearbyApartments; }
    public void setNearbyApartments(List<Apartment> nearbyApartments) { this.nearbyApartments = nearbyApartments; }
}