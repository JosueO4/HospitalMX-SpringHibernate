package com.mycompany.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.app.entity.Padecimiento;



@Repository
public interface PadecimientoRepository extends JpaRepository<Padecimiento, Integer> {

}
