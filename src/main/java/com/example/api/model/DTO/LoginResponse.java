package com.example.api.model.DTO;

public class LoginResponse {
    private Integer userId;
    private Integer empresaId;

    public LoginResponse(Integer userId, Integer empresaId) {
        this.userId = userId;
        this.empresaId = empresaId;
    }

    public LoginResponse() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userId=" + userId +
                ", empresaId=" + empresaId +
                '}';
    }
}