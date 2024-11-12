package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Grupo;

@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Long>{

}
