package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Inventario;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Long>{

}
