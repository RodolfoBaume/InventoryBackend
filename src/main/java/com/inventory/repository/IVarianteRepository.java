package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Variante;

@Repository
public interface IVarianteRepository extends JpaRepository<Variante, Long>{

}
