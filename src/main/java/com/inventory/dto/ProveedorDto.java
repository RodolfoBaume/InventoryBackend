package com.inventory.dto;

import com.inventory.entity.Proveedor;

public record ProveedorDto(
		long idProveedor,
		String nombreProveedor,
		String direccionProveedor,
		String telefonoProveedor,
		String emailProveedor,
		boolean deleted
		) {
	public ProveedorDto(Proveedor proveedor) {
		this(proveedor.getIdProveedor(), proveedor.getNombreProveedor(), proveedor.getDireccionProveedor(), proveedor.getTelefonoProveedor(), proveedor.getEmailProveedor(), proveedor.isDeleted());
	}
}
