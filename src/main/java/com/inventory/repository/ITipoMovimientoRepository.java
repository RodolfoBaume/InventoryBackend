package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.TipoMovimiento;

@Repository
public interface ITipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long>{

}
