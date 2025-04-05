package com.mycompany.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name= "agenda")
public class Agenda implements java.io.Serializable{

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="paciente_id", referencedColumnName="cedula")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="cita_id", referencedColumnName="id")
    private Cita cita;

    public Agenda(Paciente paciente, Cita cita) {
        this.paciente = paciente;
        this.cita = cita;
    }

    public Agenda(int id, Paciente paciente, Cita cita) {
        this.id = id;
        this.paciente = paciente;
        this.cita = cita;
    }

    public Agenda() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}
