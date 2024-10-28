package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Marca;

/**
 * Repositorio para realizar operaciones CRUD en la entidad {@link Marca}.
 * Extiende {@link JpaRepository} para heredar métodos de acceso a datos comunes.
 */
@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Long> {

}
