package com.careerconnect.talent.controller;

import com.careerconnect.talent.model.Listing;
import com.careerconnect.talent.model.ApplicationRecord;
import com.careerconnect.talent.repo.ListingRepo;
import com.careerconnect.talent.repo.ApplicationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/career")
public class CareerController {
    private final ListingRepo listingRepo;
    private final ApplicationRepo appRepo;

    public CareerController(ListingRepo listingRepo, ApplicationRepo appRepo){ this.listingRepo=listingRepo; this.appRepo=appRepo; }

    @GetMapping("/listings")
    public List<Listing> all(){ return listingRepo.findAll(); }

    @PostMapping("/listings")
    public ResponseEntity<?> create(@RequestBody Listing listing, Authentication auth){
        if(auth==null) return ResponseEntity.status(401).body(Map.of("error","Unauthorized"));
        String username = (String) auth.getPrincipal();
        // only employers
        if(!auth.getAuthorities().stream().anyMatch(g->g.getAuthority().equals("ROLE_EMPLOYER"))) return ResponseEntity.status(403).body(Map.of("error","Forbidden"));
        listing.setEmployer(username);
        listingRepo.save(listing);
        return ResponseEntity.ok(listing);
    }

    @PostMapping("/listings/{id}/apply")
    public ResponseEntity<?> apply(@PathVariable Long id, @RequestBody Map<String,String> body, Authentication auth){
        if(auth==null) return ResponseEntity.status(401).body(Map.of("error","Unauthorized"));
        String username = (String) auth.getPrincipal();
        var listing = listingRepo.findById(id).orElse(null);
        if(listing==null) return ResponseEntity.badRequest().body(Map.of("error","Listing not found"));
        ApplicationRecord ar = new ApplicationRecord(listing, username, body.get("coverNote"));
        appRepo.save(ar);
        return ResponseEntity.ok(Map.of("message","applied"));
    }

    @GetMapping("/listings/{id}/applications")
    public ResponseEntity<?> applications(@PathVariable Long id, Authentication auth){
        if(auth==null) return ResponseEntity.status(401).body(Map.of("error","Unauthorized"));
        var listing = listingRepo.findById(id).orElse(null);
        if(listing==null) return ResponseEntity.badRequest().body(Map.of("error","Listing not found"));
        // only employer who posted can view applicants
        String username = (String) auth.getPrincipal();
        if(!username.equals(listing.getEmployer())) return ResponseEntity.status(403).body(Map.of("error","Forbidden"));
        return ResponseEntity.ok(appRepo.findByListingId(id));
    }
}
