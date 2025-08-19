package com.careerconnect.talent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password; // hashed (BCrypt)
    private String role; // ROLE_SEEKER, ROLE_EMPLOYER, ROLE_ADMIN

    public Account() {}
    public Account(String username, String password, String role){ this.username=username; this.password=password; this.role=role; }

    public Long getId(){return id;}
    public String getUsername(){return username;} public void setUsername(String username){this.username=username;}
    public String getPassword(){return password;} public void setPassword(String password){this.password=password;}
    public String getRole(){return role;} public void setRole(String role){this.role=role;}
}
