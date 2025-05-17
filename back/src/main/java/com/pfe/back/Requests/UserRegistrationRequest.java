package com.pfe.back.Requests;

import java.util.Set;

public class UserRegistrationRequest {
    private String username;
    private String password;
    private Set<String> roles; 
   private Long sousStock;

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

public Long getSousStock() {
    return sousStock;
}
public void setSousStock(Long sousStock) {
    this.sousStock = sousStock;
}

    
}