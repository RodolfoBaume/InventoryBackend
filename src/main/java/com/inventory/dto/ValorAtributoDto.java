package com.inventory.dto;

import com.inventory.entity.ValorAtributo;

public record ValorAtributoDto(
	    Long idValorAtributo,
	    String valor
	) {
	    public ValorAtributoDto(ValorAtributo valorAtributo) {
	        this(valorAtributo.getIdValorAtributo(), valorAtributo.getValor());
	    }
	}