package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Proveedor;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Long>{
	// Método derivado para contar proveedores sin borrado logico
    long countByDeletedFalse();
}
