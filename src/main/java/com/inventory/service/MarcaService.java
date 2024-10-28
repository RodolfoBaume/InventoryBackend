package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.MarcaDto;
import com.inventory.entity.Marca;
import com.inventory.repository.IMarcaRepository;

/**
 * Implementación del servicio para la entidad {@link Marca}.
 * Contiene la lógica de negocio para gestionar las operaciones CRUD de Marca.
 */
@Service
public class MarcaService implements IMarcaService {

    @Autowired
    private IMarcaRepository marcaRepository;

    /**
     * Consulta todas las marcas.
     * 
     * @return una lista con todas las marcas.
     */
    @Transactional(readOnly = true)
	public List<Marca> findAll() {
		return (List<Marca>) marcaRepository.findAll(Sort.by("idMarca"));
	}

    /**
     * Consulta todas las marcas con paginación.
     * 
     * @param pageable configuración de paginación.
     * @return una página con marcas.
     */
    @Transactional(readOnly = true)
    public Page<Marca> findAllPage(Pageable pageable) {
        return marcaRepository.findAll(pageable);
    }

    /**
     * Busca una marca por su ID.
     * 
     * @param idMarca ID de la marca a buscar.
     * @return la marca encontrada o null si no existe.
     */
    @Transactional(readOnly = true)
    public Marca findById(Long idMarca) {
        return marcaRepository.findById(idMarca).orElse(null);
    }

    /**
     * Crea una nueva marca.
     * 
     * @param marca DTO de la marca a crear.
     * @return la marca creada.
     */
    @Transactional
    public Marca createMarca(MarcaDto marca) {
        Marca marcaEntity = new Marca();
        marcaEntity.setMarca(marca.marca());
        return marcaRepository.save(marcaEntity);
    }

    /**
     * Elimina una marca por su ID.
     * 
     * @param idMarca ID de la marca a eliminar.
     * @return la marca eliminada o null si no existe.
     */
    @Transactional
    public Marca deleteMarca(Long idMarca) {
        Marca marca = findById(idMarca);
        if (marca != null) {
            marcaRepository.delete(marca);
        }
        return marca;
    }

    /**
     * Actualiza una marca existente.
     * 
     * @param idMarca ID de la marca a actualizar.
     * @param marca DTO con los datos nuevos.
     * @return la marca actualizada.
     * @throws NoSuchElementException si la marca no existe.
     */
    @Transactional
    public Marca updateMarca(Long idMarca, MarcaDto marca) {
        Marca marcaEntity = marcaRepository.findById(idMarca)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada con el ID: " + idMarca));
        marcaEntity.setMarca(marca.marca());
        return marcaRepository.save(marcaEntity);
    }
}