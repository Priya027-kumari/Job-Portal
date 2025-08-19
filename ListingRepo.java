package com.careerconnect.talent.repo;
import com.careerconnect.talent.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ListingRepo extends JpaRepository<Listing, Long> {
  List<Listing> findByEmployer(String employer);
}
