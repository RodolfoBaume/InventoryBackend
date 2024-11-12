package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Atributo;

public interface IAtributoRepository extends JpaRepository<Atributo, Long> {

}
