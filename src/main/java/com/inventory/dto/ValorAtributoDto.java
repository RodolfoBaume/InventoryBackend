package com.inventory.dto;

import com.inventory.entity.Atributo;
import com.inventory.entity.ValorAtributo;

public record ValorAtributoDto(
		Long idValorAtributo,
		String valor,
		Atributo atributo
		) {
	public ValorAtributoDto(ValorAtributo valorAtributo) {
		this(valorAtributo.getIdValorAtributo(), valorAtributo.getValor(), valorAtributo.getAtributo() );
	}
}
