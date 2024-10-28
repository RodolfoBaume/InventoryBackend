package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.ProveedorDto;
import com.inventory.entity.Proveedor;
import com.inventory.repository.IProveedorRepository;

@Service
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedorRepository proveedorRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) proveedorRepository.findAll(Sort.by("idProveedor"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<Proveedor> findAllPage(Pageable pageable) {
		return proveedorRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public Proveedor findById(Long idProveedor) {
		return proveedorRepository.findById(idProveedor).orElse(null);
	}

	// Crear
	@Transactional
	public Proveedor createProveedor(ProveedorDto proveedor) {
		Proveedor proveedorEntity = new Proveedor();
		proveedorEntity.setNombreProveedor(proveedor.nombreProveedor());
		proveedorEntity.setDireccionProveedor(proveedor.direccionProveedor());
		proveedorEntity.setTelefonoProveedor(proveedor.telefonoProveedor());
		proveedorEntity.setEmailProveedor(proveedor.emailProveedor());
		return proveedorRepository.save(proveedorEntity);
	}

	// Eliminar
	public Proveedor deleteProveedor(Long idProveedor) {
		proveedorRepository.deleteById(idProveedor);
		return null;
	}

	// Modificar
	@Transactional
	public Proveedor updateProveedor(Long idProveedor, ProveedorDto proveedor) {
		Proveedor proveedorEntity = proveedorRepository.findById(idProveedor).orElseThrow(
				() -> new NoSuchElementException("Proveedor no encontrado con el ID: " + idProveedor));
		proveedorEntity.setNombreProveedor(proveedor.nombreProveedor());
		proveedorEntity.setDireccionProveedor(proveedor.direccionProveedor());
		proveedorEntity.setTelefonoProveedor(proveedor.telefonoProveedor());
		proveedorEntity.setEmailProveedor(proveedor.emailProveedor());
		return proveedorRepository.save(proveedorEntity);
	}
	
	//Total proveedores
	public long countProveedores() {
        return proveedorRepository.count();
    }
}
