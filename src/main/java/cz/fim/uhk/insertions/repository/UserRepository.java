package cz.fim.uhk.insertions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.fim.uhk.insertions.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    User findByEmail(String email);
}