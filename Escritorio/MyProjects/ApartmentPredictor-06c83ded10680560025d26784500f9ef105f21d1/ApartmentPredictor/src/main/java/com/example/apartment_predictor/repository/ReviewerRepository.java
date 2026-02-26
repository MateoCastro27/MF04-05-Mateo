package com.example.apartment_predictor.repository;

import com.example.apartment_predictor.model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {}