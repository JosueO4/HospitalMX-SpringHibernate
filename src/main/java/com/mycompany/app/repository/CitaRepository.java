package com.mycompany.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycompany.app.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>{
    
    @Query("SELECT c FROM Cita c WHERE c.medico.cedula = :medico_id")
    List<Cita> findCitasByMedico(@Param("medico_id") int medico_id);

    @Query("SELECT c FROM Cita c INNER JOIN c.paciente p WHERE c.paciente.cedula = p.cedula")
    List<Cita> findPacientesCitas();

}
