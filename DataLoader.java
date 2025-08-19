package com.careerconnect.talent;

import com.careerconnect.talent.model.Listing;
import com.careerconnect.talent.model.Account;
import com.careerconnect.talent.repo.ListingRepo;
import com.careerconnect.talent.repo.AccountRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final AccountRepo accountRepo;
    private final ListingRepo listingRepo;
    public DataLoader(AccountRepo accountRepo, ListingRepo listingRepo){ this.accountRepo=accountRepo; this.listingRepo=listingRepo; }

    @PostConstruct
    public void load(){
        if(accountRepo.count()==0){
            BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
            accountRepo.save(new Account("employer_alpha", enc.encode("pass123"), "ROLE_EMPLOYER"));
            accountRepo.save(new Account("seeker_alpha", enc.encode("pass123"), "ROLE_SEEKER"));
            accountRepo.save(new Account("admin_cc", enc.encode("admin123"), "ROLE_ADMIN"));
        }
        if(listingRepo.count()==0){
            listingRepo.save(new Listing("Backend Java Engineer","Work on microservices","Java,Spring","Mumbai",70000.0,"employer_alpha"));
            listingRepo.save(new Listing("Frontend React Engineer","Build SPA","React,JS","Bengaluru",60000.0,"employer_alpha"));
        }
    }
}
