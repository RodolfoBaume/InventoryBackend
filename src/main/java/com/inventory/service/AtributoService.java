package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.AtributoDto;
import com.inventory.entity.Atributo;
import com.inventory.repository.IAtributoRepository;

/**
 * Servicio que implementa la lógica de negocio para la entidad
 * {@link Atributo}.
 */
@Service
public class AtributoService implements IAtributoService {

	@Autowired
	private IAtributoRepository atributoRepository;

	/**
	 * Consulta todos los atributos ordenados por id.
	 * 
	 * @return lista de todos los atributos
	 */
	@Transactional(readOnly = true)
	public List<Atributo> findAll() {
		return (List<Atributo>) atributoRepository.findAll(Sort.by("idAtributo"));
	}

	/**
	 * Consulta todos los atributos con soporte de paginación.
	 * 
	 * @param pageable objeto de paginación
	 * @return página de atributos
	 */
	@Transactional(readOnly = true)
	public Page<Atributo> findAllPage(Pageable pageable) {
		return atributoRepository.findAll(pageable);
	}

	/**
	 * Busca un atributo por su id.
	 * 
	 * @param idAtributo id del atributo a buscar
	 * @return el atributo si existe, de lo contrario null
	 */
	@Transactional(readOnly = true)
	public Atributo findById(Long idAtributo) {
		return atributoRepository.findById(idAtributo).orElse(null);
	}

	/**
	 * Crea un nuevo atributo a partir de un objeto AtributoDto.
	 * 
	 * @param atributo DTO con los datos del atributo a crear
	 * @return el atributo creado
	 */
	@Transactional
	public Atributo createAtributo(AtributoDto atributo) {
		Atributo atributoEntity = new Atributo();
		atributoEntity.setAtributo(atributo.atributo());
		atributoEntity.setGrupo(atributo.grupo());
		return atributoRepository.save(atributoEntity);
	}

	/**
	 * Elimina un atributo por su id.
	 * 
	 * @param idAtributo id del atributo a eliminar
	 * @return el atributo eliminado o null si no existe
	 */
	public Atributo deleteAtributo(Long idAtributo) {
		atributoRepository.deleteById(idAtributo);
		return null;
	}

	/**
	 * Actualiza un atributo existente a partir de un DTO y su id.
	 * 
	 * @param idAtributo id del atributo a actualizar
	 * @param atributo DTO con los nuevos datos del atributo
	 * @return el atributo actualizado
	 */
	@Transactional
	public Atributo updateAtributo(Long idAtributo, AtributoDto atributo) {
		Atributo atributoEntity = atributoRepository.findById(idAtributo)
				.orElseThrow(() -> new NoSuchElementException("Atributo no encontrado con el ID: " + idAtributo));
		atributoEntity.setAtributo(atributo.atributo());
		atributoEntity.setGrupo(atributo.grupo());
		return atributoRepository.save(atributoEntity);
	}
}
