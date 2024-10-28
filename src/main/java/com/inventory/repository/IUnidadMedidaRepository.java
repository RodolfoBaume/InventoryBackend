package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.UnidadMedida;

@Repository
public interface IUnidadMedidaRepository extends JpaRepository<UnidadMedida, Long>{

}
