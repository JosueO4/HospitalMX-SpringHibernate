package com.mycompany.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycompany.app.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    @Query("SELECT a FROM Agenda a WHERE a.paciente.cedula = :paciente_id")
    List<Agenda> findAgendaByPaciente(@Param("paciente_id") int paciente_id);
}
