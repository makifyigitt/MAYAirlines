package com.may.MAYAirlines.token;

import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer id;

    @Column(name = "token",
            unique = true)
    public String token;

    @Column(name = "revoked" )
    public boolean revoked;
    @Column(name = "expired")
    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;


    public Token() {
    }

    public Token(String token, boolean revoked, boolean expired, User user) {
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
    }

    public Token(String token, boolean revoked, boolean expired, Customer customer) {
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
