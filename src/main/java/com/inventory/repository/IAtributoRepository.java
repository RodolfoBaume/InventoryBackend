package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.entity.Atributo;

@Repository
public interface IAtributoRepository extends JpaRepository<Atributo, Long> {

	List<Atributo> findByGrupo_IdGrupo(Long grupoId);

}
