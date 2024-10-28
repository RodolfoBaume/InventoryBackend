package com.inventory.dto;

import com.inventory.entity.Almacen;

/**
 * DTO para la transferencia de datos de la entidad {@link Almacen}.
 */
public record AlmacenDto(
		long idAlmacen,
		String nombreAlmacen,
		String direccionAlmacen,
		String telefonoAlmacen,
		String responsableAlmacen
		) {
	/**
     * Constructor que inicializa un AlmacenDto a partir de un objeto Almacen.
     *
     * @param almacen entidad Almacen para crear el DTO
     */
	public AlmacenDto(Almacen almacen) {
		this(almacen.getIdAlmacen(), almacen.getNombreAlmacen(), almacen.getDireccionAlmacen(), almacen.getTelefonoAlmacen(), almacen.getResponsableAlmacen());
	}
}
