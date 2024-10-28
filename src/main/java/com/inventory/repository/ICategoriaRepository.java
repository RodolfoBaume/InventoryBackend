package com.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
	List<Categoria> findByFolderTrue(); // Para obtener solo los nodos padre

	@Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.productos WHERE c.folder = false")
	List<Categoria> obtenerCategoriasConProductos();

	//consulta por id categoria
	@Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.productos WHERE c.folder = false AND c.idCategoria = :idCategoria")
	Optional<Categoria> obtenerCategoriaConProductosPorId(@Param("idCategoria") Long idCategoria);


}
