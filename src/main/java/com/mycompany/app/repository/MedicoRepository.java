package com.mycompany.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.app.entity.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
}
