package com.patryk.app.webapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patryk.app.webapp.Model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    List<User> findAllByNameOrEmail(String name, String email);
}
