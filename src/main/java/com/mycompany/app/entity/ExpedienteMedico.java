package com.mycompany.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="expediente_medico")
public class ExpedienteMedico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name="paciente_id")
    Paciente paciente;
    @ManyToOne
    @JoinColumn(name="padecimiento_id")
    Padecimiento padecimiento;
    @ManyToOne
    @JoinColumn(name="medicamento_id")
    Medicamento medicamento;
    @ManyToOne
    @JoinColumn(name="procedimiento_id")
    Procedimiento procedimiento;

    public ExpedienteMedico(String fecha, Paciente paciente,Padecimiento padecimiento, Medicamento medicamento, Procedimiento procedimiento) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.padecimiento = padecimiento;
        this.medicamento = medicamento;
        this.procedimiento = procedimiento;
    }

    public ExpedienteMedico() {
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Padecimiento getPadecimiento() {
        return padecimiento;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }   

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPadecimiento(Padecimiento padecimiento) {
        this.padecimiento = padecimiento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
