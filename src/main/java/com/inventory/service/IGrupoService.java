package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.GrupoDto;
import com.inventory.entity.Grupo;

public interface IGrupoService {

	List<Grupo> findAll();
	
	Page<Grupo> findAllPage(Pageable pageable);
	
	Grupo findById(Long idGrupo);
	
	Grupo createGrupo(GrupoDto grupo);
	
	Grupo deleteGrupo(Long idGrupo);
	
	Grupo updateGrupo(Long idGrupo, GrupoDto grupo);
		
	GrupoDto obtenerGrupoCompleto(Long idGrupo);
	
}
