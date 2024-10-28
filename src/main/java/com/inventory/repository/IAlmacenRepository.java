package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Almacen;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link Almacen}.
 * 
 * Esta interfaz extiende {@link JpaRepository}, lo que permite realizar operaciones
 * de base de datos sobre la tabla 'almacenes' sin necesidad de implementar métodos manualmente.
 */
@Repository
public interface IAlmacenRepository extends JpaRepository<Almacen, Long>{

}
