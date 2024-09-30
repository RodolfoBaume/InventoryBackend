package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.TipoMovimiento;

public interface ITipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long>{

}
