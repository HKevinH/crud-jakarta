package com.crud.redes.service;

import com.crud.redes.models.Users;
import com.crud.redes.persitence.UserSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {
    @Inject
    private UserSession userSession;

    @Inject
    private UserService userService;

    public boolean authenticate(String email, String password) {
        Users user = userService.loginUser(email, password);
        userSession.setUser(user);
        return user != null ? true : false;
    }
}
