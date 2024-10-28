package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.AlmacenDto;
import com.inventory.entity.Almacen;
import com.inventory.repository.IAlmacenRepository;

/**
 * Servicio que implementa la lógica de negocio para la entidad {@link Almacen}.
 */
@Service
public class AlmacenService implements IAlmacenService {

	@Autowired
	private IAlmacenRepository almacenRepository;

	/**
     * Consulta todos los almacenes ordenados por id.
     * 
     * @return lista de todos los almacenes
     */
	@Transactional(readOnly = true)
	public List<Almacen> findAll() {
		return (List<Almacen>) almacenRepository.findAll(Sort.by("idAlmacen"));
	}

	/**
     * Consulta todos los almacenes con soporte de paginación.
     * 
     * @param pageable objeto de paginación
     * @return página de almacenes
     */
	@Transactional(readOnly = true)
	public Page<Almacen> findAllPage(Pageable pageable) {
		return almacenRepository.findAll(pageable);
	}

	/**
     * Busca un almacén por su id.
     * 
     * @param idAlmacen id del almacén a buscar
     * @return el almacén si existe, de lo contrario null
     */
	@Transactional(readOnly = true)
	public Almacen findById(Long idAlmacen) {
		return almacenRepository.findById(idAlmacen).orElse(null);
	}

	/**
     * Crea un nuevo almacén a partir de un objeto AlmacenDto.
     * 
     * @param almacen DTO con los datos del almacén a crear
     * @return el almacén creado
     */
	@Transactional
	public Almacen createAlmacen(AlmacenDto almacen) {
		Almacen almacenEntity = new Almacen();
		almacenEntity.setNombreAlmacen(almacen.nombreAlmacen());
		almacenEntity.setDireccionAlmacen(almacen.direccionAlmacen());
		almacenEntity.setResponsableAlmacen(almacen.responsableAlmacen());
		almacenEntity.setTelefonoAlmacen(almacen.telefonoAlmacen());
		return almacenRepository.save(almacenEntity);
	}

	/**
     * Elimina un almacén por su id.
     * 
     * @param idAlmacen id del almacén a eliminar
     * @return el almacén eliminado o null si no existe
     */
	public Almacen deleteAlmacen(Long idAlmacen) {
		almacenRepository.deleteById(idAlmacen);
		return null;
	}

	/**
     * Actualiza un almacén existente a partir de un DTO y su id.
     * 
     * @param idAlmacen id del almacén a actualizar
     * @param almacen DTO con los nuevos datos del almacén
     * @return el almacén actualizado
     */
	@Transactional
	public Almacen updateAlmacen(Long idAlmacen, AlmacenDto almacen) {
		Almacen almacenEntity = almacenRepository.findById(idAlmacen)
				.orElseThrow(() -> new NoSuchElementException("Almacen no encontrado con el ID: " + idAlmacen));
		almacenEntity.setNombreAlmacen(almacen.nombreAlmacen());
		almacenEntity.setDireccionAlmacen(almacen.direccionAlmacen());
		almacenEntity.setResponsableAlmacen(almacen.responsableAlmacen());
		almacenEntity.setTelefonoAlmacen(almacen.telefonoAlmacen());
		return almacenRepository.save(almacenEntity);
	}
}
