package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

}
