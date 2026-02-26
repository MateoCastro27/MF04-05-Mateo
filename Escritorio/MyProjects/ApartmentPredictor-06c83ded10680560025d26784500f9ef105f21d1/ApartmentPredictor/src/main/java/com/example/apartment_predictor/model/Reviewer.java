package com.example.apartment_predictor.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reviewer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewType;
    private int experienceYears;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews = new ArrayList<>();

    public Reviewer() {
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // toString para depuración (usa getters de Person para name/email/age)
    @Override
    public String toString() {
        return "Reviewer{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", age=" + getAge() +
                ", reviewType='" + reviewType + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}