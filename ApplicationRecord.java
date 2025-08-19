package com.careerconnect.talent.model;

import jakarta.persistence.*;

@Entity
public class ApplicationRecord {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  private Listing listing;
  private String applicant; // username
  @Column(length=2000)
  private String coverNote;

  public ApplicationRecord() {}
  public ApplicationRecord(Listing listing, String applicant, String coverNote){ this.listing=listing; this.applicant=applicant; this.coverNote=coverNote; }

  public Long getId(){return id;}
  public Listing getListing(){return listing;} public void setListing(Listing listing){this.listing=listing;}
  public String getApplicant(){return applicant;} public void setApplicant(String applicant){this.applicant=applicant;}
  public String getCoverNote(){return coverNote;} public void setCoverNote(String coverNote){this.coverNote=coverNote;}
}
