package com.inventory.service;

import java.util.List;

import com.inventory.entity.CombinacionAtributos;

public interface ICombinacionAtributosService {

	CombinacionAtributos createCombinacion(Long grupoId, List<Long> valorAtributoIds);
	
}
