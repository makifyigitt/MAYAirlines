////package com.may.MAYAirlines.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//
//@Entity
//@Table(name = "emails")
//public class Email {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private int id;
//    @jakarta.validation.constraints.Email
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "user_id")
//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",nullable = false)
//    private MainUser mainUser;
//
//
//}
