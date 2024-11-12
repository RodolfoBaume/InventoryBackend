package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.CombinacionValorAtributo;

@Repository
public interface ICombinacionValorAtributoRepository extends JpaRepository<CombinacionValorAtributo, Long>{

}
