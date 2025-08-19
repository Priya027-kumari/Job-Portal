package com.careerconnect.talent.repo;
import com.careerconnect.talent.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepo extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
  boolean existsByUsername(String username);
}
