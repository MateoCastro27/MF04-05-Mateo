package com.example.apartment_predictor.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractId;

    private LocalDate startDate;
    private LocalDate endDate;

    // Relación: muchos contratos pueden pertenecer a un apartamento
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")   // columna FK en la tabla contracts
    private Apartment apartment;

    // Constructor vacío (obligatorio para JPA)
    public Contract() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", contractId='" + contractId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}