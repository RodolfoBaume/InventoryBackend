package com.inventory.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.GrupoDto;
import com.inventory.entity.Grupo;
import com.inventory.repository.IGrupoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GrupoService implements IGrupoService{

	@Autowired
	private IGrupoRepository grupoRepository;
	

	// Consulta todos
	@Transactional(readOnly = true)
	public List<Grupo> findAll() {
		return (List<Grupo>) grupoRepository.findAll(Sort.by("idGrupo"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<Grupo> findAllPage(Pageable pageable) {
		return grupoRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public Grupo findById(Long idGrupo) {
		return grupoRepository.findById(idGrupo).orElse(null);
	}

	// Crear
	@Transactional
	public Grupo createGrupo(GrupoDto grupo) {
		Grupo grupoEntity = new Grupo();
		grupoEntity.setNombreGrupo(grupo.nombreGrupo());
		grupoEntity.setStatus(grupo.status());
		return grupoRepository.save(grupoEntity);
	}

	// Eliminar
	public Grupo deleteGrupo(Long idGrupo) {
		grupoRepository.deleteById(idGrupo);
		return null;
	}

	// Modificar
	@Transactional
	public Grupo updateGrupo(Long idGrupo, GrupoDto grupo) {
		Grupo grupoEntity = grupoRepository.findById(idGrupo).orElseThrow(
				() -> new NoSuchElementException("Grupo no encontrado con el ID: " + idGrupo));
		grupoEntity.setNombreGrupo(grupo.nombreGrupo()); 
		grupoEntity.setStatus(grupo.status());
		return grupoRepository.save(grupoEntity);
	}
	
	

	
	public GrupoDto obtenerGrupoCompleto(Long idGrupo) {
	    Grupo grupo = grupoRepository.findById(idGrupo)
	            .orElseThrow(() -> new EntityNotFoundException("Grupo con ID " + idGrupo + " no encontrado"));
	    
	    // Forzar inicialización de relaciones
	    grupo.getAtributos().forEach(atributo -> atributo.getValores().size());
	    
	    return new GrupoDto(grupo);
	}
	
	
	
}
