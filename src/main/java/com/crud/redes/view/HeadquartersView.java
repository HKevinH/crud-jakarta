package com.crud.redes.view;

import com.crud.redes.config.HibernateUtil;
import com.crud.redes.models.Headquarters;
import com.crud.redes.service.HeadquartersService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("headquartersView")
@Data
@ViewScoped
public class HeadquartersView implements Serializable {

    @Inject
    private HeadquartersService headquartersService;
    private List<Headquarters> listHeadquarters = new ArrayList<>();
    private Headquarters selected;
    private Headquarters newHeadquarters = new Headquarters();

    @PostConstruct
    public void init() {
        get_list();
        if (this.selected == null) {
            this.selected = new Headquarters();
        }
    }

    public void get_list() {
        this.listHeadquarters = headquartersService.getAll();
    }

    public void update(){
        if (selected == null || selected.getId() == null) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Selección inválida", "Por favor selecciona una sede válida para actualizar.");
            return;
        }
        headquartersService.update(selected);
        get_list();
        addMsg(FacesMessage.SEVERITY_INFO, "Sede actualizada", "La sede ha sido actualizada exitosamente.");
    }

    public void delete(Headquarters hd){
        headquartersService.delete(hd);
        get_list();
        addMsg(FacesMessage.SEVERITY_INFO, "Sede eliminada", "La sede ha sido eliminada exitosamente.");
        selected = new Headquarters();
    }

    public void save(){
        headquartersService.create(newHeadquarters);
        newHeadquarters = new Headquarters();
        get_list();
        addMsg(FacesMessage.SEVERITY_INFO, "Sede creada", "La sede ha sido creada exitosamente.");
    }

    private void addMsg(FacesMessage.Severity sev, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sev, summary, detail));
    }
}
