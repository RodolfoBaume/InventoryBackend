package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.ProveedorDto;
import com.inventory.entity.Proveedor;

public interface IProveedorService {
	List<Proveedor> findAll();
	
	Page<Proveedor> findAllPage(Pageable pageable);
	
	Proveedor findById(Long idCliente);
	
	Proveedor createProveedor(ProveedorDto proveedor);
	
	Proveedor deleteProveedor(Long idProveedor);
	
	Proveedor updateProveedor(Long idProveedor, ProveedorDto proveedor);
}
