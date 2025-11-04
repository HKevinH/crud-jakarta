package com.crud.redes.persitence;

import com.crud.redes.models.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named("userSession")
@SessionScoped
@Data
public class UserSession implements Serializable {
    private Users user;

    public String logout() {
        user = null;
        return "login.xhtml?faces-redirect=true";
    }
}
