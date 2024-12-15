package com.example.api.services;

import com.example.api.Repositories.AuthRepository;
import com.example.api.model.Admin;
import com.example.api.model.DTO.LoginResponse;
import com.example.api.model.DTO.Login_Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private AuthRepository userRepository;

    public LoginResponse authenticateUser(Login_Request loginRequest) throws Exception {
        Admin admin = userRepository.findByUsernameAndPasswordAndDni(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                loginRequest.getDni()
        ).orElseThrow(() -> new Exception("Invalid credentials"));

        return new LoginResponse(admin.getId(), admin.getIdEmpresa().getId());
    }
}