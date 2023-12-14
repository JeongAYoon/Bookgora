package com.mysite.sbb.Repository;

import com.mysite.sbb.Entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByUsernameAndPassword(final String username, final String password);
    Optional<SiteUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}