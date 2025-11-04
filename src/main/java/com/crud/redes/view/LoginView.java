package com.crud.redes.view;

import jakarta.enterprise.context.SessionScoped; // o ViewScoped si prefieres por vista
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import com.crud.redes.persitence.*;

import com.crud.redes.service.AuthService;
import jakarta.faces.context.FacesContext;
import lombok.Data;

@Named("loginView")
@SessionScoped
@Data
public class LoginView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private UserSession userSession;

    private String username;
    private String password;
    private boolean rememberMe;

    @Inject
    private AuthService authService;

    public void doLogin() throws IOException {
        if (authService.authenticate(username, password)) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("user", username);

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("home.xhtml?faces-redirect=true");
        } else {
            // Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(
                            jakarta.faces.application.FacesMessage.SEVERITY_ERROR,
                            "Credenciales inválidas", "Usuario o contraseña incorrectos"));
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?faces-redirect=true");
    }
}
