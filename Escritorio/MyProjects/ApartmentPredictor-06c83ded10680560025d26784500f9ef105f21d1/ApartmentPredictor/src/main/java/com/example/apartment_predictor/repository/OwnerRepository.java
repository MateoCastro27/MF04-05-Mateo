package com.example.apartment_predictor.repository;

import com.example.apartment_predictor.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {}