package com.crud.redes.view;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("layoutView")
@ViewScoped
public class LayoutView implements Serializable {
    private String current;

    @PostConstruct
    public void init() {
        current = "/sections/home.xhtml";
    }

    public void load(String page) {
        this.current = page;
    }

    public String getCurrent() { return current; }
}
