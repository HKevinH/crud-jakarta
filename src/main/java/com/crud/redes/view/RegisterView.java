package com.crud.redes.view;

import com.crud.redes.models.Users;
import com.crud.redes.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named("registerView")
@SessionScoped
@Data
public class RegisterView implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
    private String confirm;
    private String role;

    @Inject
    UserService userService;

    public void register() {
        Users newUser = new Users();
        if (name == null || name.isBlank()) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Usuario requerido", "Ingresa un nombre de usuario.");
            return;
        }
        if (email == null || !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$")) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Email inválido", "Ingresa un correo válido.");
            return;
        }
        if (password == null || password.length() < 6) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Contraseña débil", "Mínimo 6 caracteres.");
            return;
        }
        if (!password.equals(confirm)) {
            addMsg(FacesMessage.SEVERITY_ERROR, "No coinciden", "Las contraseñas no coinciden.");
            return;
        }
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRol(null);

        boolean success = userService.registerUser(newUser);
        if (success) {
            System.out.println("Usuario registrado exitosamente: " + name);
            addMsg(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Usuario " + name + " registrado.");
        } else {
            System.out.println("Error al registrar el usuario: " + name);
            addMsg(FacesMessage.SEVERITY_ERROR, "Error de registro", "No se pudo registrar el usuario.");
        }
    }

    private void addMsg(FacesMessage.Severity sev, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sev, summary, detail));
    }
}
