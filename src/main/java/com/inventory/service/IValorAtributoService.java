package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.AtributoDto;
import com.inventory.dto.ValorAtributoDto;
import com.inventory.entity.Atributo;
import com.inventory.entity.ValorAtributo;

/**
 * Interfaz de servicio para la entidad {@link ValorAtributo}.
 */
public interface IValorAtributoService {

	/**
     * Consulta todos los valores atributos.
     *
     * @return lista de valores atributos
     */
    List<ValorAtributo> findAll();

    /**
     * Consulta todos los valores atributos con soporte de paginación.
     *
     * @param pageable objeto de paginación
     * @return página de valores atributos
     */
    Page<ValorAtributo> findAllPage(Pageable pageable);

    /**
     * Busca un valore atributo por su id.
     *
     * @param idValorAtributo id del valor atributo a buscar
     * @return el valorAtributo si existe
     */
    ValorAtributo findById(Long idValorAtributo);

    /**
     * Crea un nuevo valorAtributo.
     *
     * @param valorAtributo DTO con datos del valorAtributo a crear
     * @return el valorAtributo creado
     */
    //Atributo createAtributo(AtributoDto atributo);
    ValorAtributo createValorAtributo(Long atributoId, ValorAtributo valorAtributo);

    
    
    List<ValorAtributo> getValoresAtributosByAtributo(Long atributoId);
    
    /**
     * Elimina un valorAtributo por su id.
     *
     * @param idValorAtributo id del valorAtributo a eliminar
     * @return el valorAtributo eliminado
     */
    ValorAtributo deleteValorAtributo(Long idValorAtributo);

    /**
     * Actualiza un valor atributo existente.
     *
     * @param idValorAtributo id del valorAtributo a actualizar
     * @param valorAtributo DTO con los nuevos datos
     * @return el valorAtributo actualizado
     */
    ValorAtributo updateValorAtributo(Long idValorAtributo, ValorAtributoDto valorAtributo);
}
