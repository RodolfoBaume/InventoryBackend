package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
/*
	@Query("SELECT p FROM Producto p " +
	           "JOIN p.tipoProducto tp " +
	           "JOIN tp.categoria c " +
	           "WHERE c.idCategoria = :idCategoria")
	    List<Producto> obtenerProductosPorCategoriaId(@Param("idCategoria") Long idCategoria);
*/
	
}
