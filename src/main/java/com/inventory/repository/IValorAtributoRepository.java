package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.ValorAtributo;

@Repository
public interface IValorAtributoRepository extends JpaRepository<ValorAtributo, Long>{

	List<ValorAtributo> findByAtributo_IdAtributo(Long atributoId);

}
