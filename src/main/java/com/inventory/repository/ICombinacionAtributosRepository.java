package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.CombinacionAtributos;

@Repository
public interface ICombinacionAtributosRepository extends JpaRepository<CombinacionAtributos, Long>{

}
