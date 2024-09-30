package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Ubicacion;

@Repository
public interface IUbicacionRepository extends JpaRepository<Ubicacion, Long>{

}
