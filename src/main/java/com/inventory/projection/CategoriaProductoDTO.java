package com.inventory.projection;

import java.util.List;

public record CategoriaProductoDTO(
		Long idCategoria,
	    String nombreCategoria,
	    List<ProductoVistaDto> productos) {
	
}


