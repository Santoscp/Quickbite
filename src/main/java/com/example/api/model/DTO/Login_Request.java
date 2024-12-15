package com.example.api.model.DTO;

public class Login_Request {
    private String username;
    private String password;
    private String dni;

    public Login_Request(String username, String password, String dni) {
        this.username = username;
        this.password = password;
        this.dni = dni;
    }

    public Login_Request() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Login_Request{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
