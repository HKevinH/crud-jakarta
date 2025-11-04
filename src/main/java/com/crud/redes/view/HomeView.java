package com.crud.redes.view;

import com.crud.redes.models.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named("homeView")
@SessionScoped
@Data
public class HomeView implements Serializable {
    private Users user;
}
