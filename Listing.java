package com.careerconnect.talent.model;

import jakarta.persistence.*;

@Entity
public class Listing {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Column(length=2000)
  private String description;
  private String skills;
  private String location;
  private Double salary;
  private String employer; // username of poster

  public Listing() {}
  public Listing(String title, String description, String skills, String location, Double salary, String employer){
    this.title=title; this.description=description; this.skills=skills; this.location=location; this.salary=salary; this.employer=employer;
  }

  public Long getId(){return id;}
  public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
  public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
  public String getSkills(){return skills;} public void setSkills(String skills){this.skills=skills;}
  public String getLocation(){return location;} public void setLocation(String location){this.location=location;}
  public Double getSalary(){return salary;} public void setSalary(Double salary){this.salary=salary;}
  public String getEmployer(){return employer;} public void setEmployer(String employer){this.employer=employer;}
}
