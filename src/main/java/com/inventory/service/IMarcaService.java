package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.MarcaDto;
import com.inventory.entity.Marca;

/**
 * Interface de servicio que define las operaciones para la entidad {@link Marca}.
 * Métodos incluidos:
 * - Búsqueda de todas las marcas.
 * - Paginación.
 * - Creación, eliminación y actualización de marcas.
 */
public interface IMarcaService {

    /**
     * Consulta todas las marcas.
     * 
     * @return una lista con todas las marcas.
     */
    List<Marca> findAll();

    /**
     * Consulta todas las marcas con paginación.
     * 
     * @param pageable objeto de paginación que contiene página, tamaño, etc.
     * @return una página con marcas.
     */
    Page<Marca> findAllPage(Pageable pageable);

    /**
     * Busca una marca por su ID.
     * 
     * @param idMarca ID de la marca a buscar.
     * @return la marca encontrada o null si no existe.
     */
    Marca findById(Long idMarca);

    /**
     * Crea una nueva marca.
     * 
     * @param marca DTO de la marca a crear.
     * @return la marca creada.
     */
    Marca createMarca(MarcaDto marca);

    /**
     * Elimina una marca por su ID.
     * 
     * @param idMarca ID de la marca a eliminar.
     * @return la marca eliminada o null si no existe.
     */
    Marca deleteMarca(Long idMarca);

    /**
     * Actualiza una marca existente.
     * 
     * @param idMarca ID de la marca a actualizar.
     * @param marca DTO de la marca con la información actualizada.
     * @return la marca actualizada.
     */
    Marca updateMarca(Long idMarca, MarcaDto marca);
}
