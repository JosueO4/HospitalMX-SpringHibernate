package com.mycompany.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medicamento")
public class Medicamento {
    


    @Id
    @Column(name = "id")
    private int id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="dosis")
    private String dosis;

    public Medicamento(String nombre, String dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
    }

    public Medicamento() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDosis(){
        return dosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setDosis(String dosis){
        this.dosis = dosis;
    }


}