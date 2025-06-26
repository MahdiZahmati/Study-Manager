package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Professor(String firstName, String lastName, String email, long nationalId,
                     String phoneNumber, String address, String city) {
        super(firstName, lastName, email, nationalId, phoneNumber, address, city);
    }

    public Professor() {

    }
}
