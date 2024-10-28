package com.inventory.projection;

public record ProductoVistaDto(    
		Long idProducto,
	    String sku,
	    String nombreProducto,
	    String descripcionProducto,
	    double precio,
	    int cantidad,
	    int minimo, 
	    int maximo,
	    String unidadMedida
	    ) {

	

}
