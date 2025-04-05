package com.mycompany.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.app.entity.Procedimiento;

@Repository
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Integer> {
    
}
