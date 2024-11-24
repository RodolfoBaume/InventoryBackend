package com.inventory.dto;

import com.inventory.entity.Producto;

public record ProductosDto(
	    long idProducto,
	    String nombreProducto,
	    double precio,
	    int cantidad
	) {
	    public ProductosDto(Producto producto) {
	        this(
	            producto.getIdProducto(),
	            producto.getNombreProducto(),
	            producto.getPrecio(),
	            producto.getCantidad()
	        );
	    }
	}

