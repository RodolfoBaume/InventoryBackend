package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.TipoProducto;

@Repository
public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Long>{

}
