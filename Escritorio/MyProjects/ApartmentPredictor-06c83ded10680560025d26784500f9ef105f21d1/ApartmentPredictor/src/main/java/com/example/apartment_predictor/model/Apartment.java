package com.example.apartment_predictor.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Apartment {

    @Id
    private String id;

    private Long price;
    private Integer area;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    private String hotwaterheating;
    private String airconditioning;
    private Integer parking;
    private String prefarea;
    private String furnishingstatus;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "apartment_school",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id")
    )
    private List<School> nearbySchools = new ArrayList<>();

    public Apartment() {
        this.id = UUID.randomUUID().toString();
    }

    public Apartment(Long price, Integer area, Integer bedrooms, Integer bathrooms,
                     Integer stories, String mainroad, String guestroom, String basement,
                     String hotwaterheating, String airconditioning,
                     Integer parking, String prefarea, String furnishingstatus) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.mainroad = mainroad;
        this.guestroom = guestroom;
        this.basement = basement;
        this.hotwaterheating = hotwaterheating;
        this.airconditioning = airconditioning;
        this.parking = parking;
        this.prefarea = prefarea;
        this.furnishingstatus = furnishingstatus;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price; }

    public Integer getArea() { return area; }
    public void setArea(Integer area) { this.area = area; }

    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }

    public Integer getBathrooms() { return bathrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }

    public Integer getStories() { return stories; }
    public void setStories(Integer stories) { this.stories = stories; }

    public String getMainroad() { return mainroad; }
    public void setMainroad(String mainroad) { this.mainroad = mainroad; }

    public String getGuestroom() { return guestroom; }
    public void setGuestroom(String guestroom) { this.guestroom = guestroom; }

    public String getBasement() { return basement; }
    public void setBasement(String basement) { this.basement = basement; }

    public String getHotwaterheating() { return hotwaterheating; }
    public void setHotwaterheating(String hotwaterheating) { this.hotwaterheating = hotwaterheating; }

    public String getAirconditioning() { return airconditioning; }
    public void setAirconditioning(String airconditioning) { this.airconditioning = airconditioning; }

    public Integer getParking() { return parking; }
    public void setParking(Integer parking) { this.parking = parking; }

    public String getPrefarea() { return prefarea; }
    public void setPrefarea(String prefarea) { this.prefarea = prefarea; }

    public String getFurnishingstatus() { return furnishingstatus; }
    public void setFurnishingstatus(String furnishingstatus) { this.furnishingstatus = furnishingstatus; }

    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public List<School> getNearbySchools() { return nearbySchools; }
    public void setNearbySchools(List<School> nearbySchools) { this.nearbySchools = nearbySchools; }

    @Override
    public String toString() {
        return "Apartment{id='" + id + "', price=" + price + ", area=" + area + "}";
    }
}