package com.mycompany.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.app.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{
    
}
