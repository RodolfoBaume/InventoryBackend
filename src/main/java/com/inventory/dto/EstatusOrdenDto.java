package com.inventory.dto;

import com.inventory.entity.EstatusOrden;

public record EstatusOrdenDto(
		long idEstatusOrden,
		String estatusOrden
		) {

	public EstatusOrdenDto(EstatusOrden estatusOrden) {
		this(estatusOrden.getIdEstatusOrden(), estatusOrden.getEstatusOrden());
	}
}
