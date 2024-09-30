package com.inventory.dto;

import com.inventory.entity.TipoMovimiento;

public record TipoMovimientoDto(
		long idTipoMovimiento,
		String tipoMovimiento
		) {
	public TipoMovimientoDto(TipoMovimiento tipoMovimiento) {
		this(tipoMovimiento.getIdTipoMovimiento(), tipoMovimiento.getTipoMovimiento());
	}
}
