package com.inventory.dto;

import com.inventory.entity.Ubicacion;

public record UbicacionDto(
		long idUbicacion,
		String nombreUbicacion,
		String direccionUbicacion
		) {
	public UbicacionDto(Ubicacion ubicacion) {
		this(ubicacion.getIdUbicacion(), ubicacion.getNombreUbicacion(), ubicacion.getDireccionUbicacion());
	}
}
