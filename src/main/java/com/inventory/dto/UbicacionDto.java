package com.inventory.dto;

import com.inventory.entity.Ubicacion;

public record UbicacionDto(
		long idUbicacion,
		String nombreUbicacion,
		String direccionUbicacion,
		String telefono,
		String responsable
		) {
	public UbicacionDto(Ubicacion ubicacion) {
		this(ubicacion.getIdUbicacion(), ubicacion.getNombreUbicacion(), ubicacion.getDireccionUbicacion(), ubicacion.getTelefono(), ubicacion.getResponsable());
	}
}
