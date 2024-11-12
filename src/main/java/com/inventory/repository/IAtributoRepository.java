package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Atributo;

public interface IAtributoRepository extends JpaRepository<Atributo, Long> {

	List<Atributo> findByGrupo_IdGrupo(Long grupoId);

}
