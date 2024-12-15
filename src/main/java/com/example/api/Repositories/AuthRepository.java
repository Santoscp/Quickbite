package com.example.api.Repositories;

import com.example.api.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsernameAndPasswordAndDni(String username, String password, String dni);
}