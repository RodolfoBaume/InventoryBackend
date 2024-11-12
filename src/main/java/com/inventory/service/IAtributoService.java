package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.AtributoDto;
import com.inventory.entity.Atributo;

/**
 * Interfaz de servicio para la entidad {@link Atributo}.
 */
public interface IAtributoService {
	/**
     * Consulta todos los atributos.
     *
     * @return lista de atributos
     */
    List<Atributo> findAll();

    /**
     * Consulta todos los atributos con soporte de paginación.
     *
     * @param pageable objeto de paginación
     * @return página de atributos
     */
    Page<Atributo> findAllPage(Pageable pageable);

    /**
     * Busca un atributo por su id.
     *
     * @param idAtributo id del atributo a buscar
     * @return el atributo si existe
     */
    Atributo findById(Long idAtributo);

    /**
     * Crea un nuevo atributo.
     *
     * @param atributo DTO con datos del atributo a crear
     * @return el atributo creado
     */
    //Atributo createAtributo(AtributoDto atributo);
    Atributo createAtributo(Long grupoId, Atributo atributo);

    
    
    List<Atributo> getAtributosByGrupo(Long grupoId);
    
    /**
     * Elimina un atributo por su id.
     *
     * @param idAtributo id del atributo a eliminar
     * @return el atributo eliminado
     */
    Atributo deleteAtributo(Long idAtributo);

    /**
     * Actualiza un atributo existente.
     *
     * @param idAtributo id del atributo a actualizar
     * @param Atributo DTO con los nuevos datos
     * @return el atributo actualizado
     */
    Atributo updateAtributo(Long idAtributo, AtributoDto atributo);
}
