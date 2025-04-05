package com.mycompany.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.mycompany.app.entity.ExpedienteMedico;

public interface ExpedienteMedicoRepository extends JpaRepository<ExpedienteMedico, Integer>{
    @Query("SELECT e FROM ExpedienteMedico e WHERE e.paciente.cedula = :paciente_id")
    List<ExpedienteMedico> findExpedienteByPaciente(@Param("paciente_id") int paciente_id);
}
