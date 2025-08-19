package com.careerconnect.talent.repo;
import com.careerconnect.talent.model.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepo extends JpaRepository<ApplicationRecord, Long> {
  List<ApplicationRecord> findByListingId(Long listingId);
  List<ApplicationRecord> findByApplicant(String username);
}
