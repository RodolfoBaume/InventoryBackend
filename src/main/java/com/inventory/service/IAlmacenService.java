package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.AlmacenDto;
import com.inventory.entity.Almacen;

/**
 * Interfaz de servicio para la entidad {@link Almacen}.
 */
public interface IAlmacenService {

    /**
     * Consulta todos los almacenes.
     *
     * @return lista de almacenes
     */
    List<Almacen> findAll();

    /**
     * Consulta todos los almacenes con soporte de paginación.
     *
     * @param pageable objeto de paginación
     * @return página de almacenes
     */
    Page<Almacen> findAllPage(Pageable pageable);

    /**
     * Busca un almacén por su id.
     *
     * @param idAlmacen id del almacén a buscar
     * @return el almacén si existe
     */
    Almacen findById(Long idAlmacen);

    /**
     * Crea un nuevo almacén.
     *
     * @param almacen DTO con datos del almacén a crear
     * @return el almacén creado
     */
    Almacen createAlmacen(AlmacenDto almacen);

    /**
     * Elimina un almacén por su id.
     *
     * @param idAlmacen id del almacén a eliminar
     * @return el almacén eliminado
     */
    Almacen deleteAlmacen(Long idAlmacen);

    /**
     * Actualiza un almacén existente.
     *
     * @param idAlmacen id del almacén a actualizar
     * @param almacen DTO con los nuevos datos
     * @return el almacén actualizado
     */
    Almacen updateAlmacen(Long idAlmacen, AlmacenDto almacen);
}
