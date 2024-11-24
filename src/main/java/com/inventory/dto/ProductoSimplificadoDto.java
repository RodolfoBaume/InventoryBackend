package com.inventory.dto;

import java.util.Date;

import com.inventory.entity.Marca;
import com.inventory.entity.Producto;
import com.inventory.entity.UnidadMedida;

public record ProductoSimplificadoDto(
	    long idProducto,
	    String sku,
	    String nombreProducto,
	    String descripcionProducto,
	    double precio,
	    int cantidad,
	    int minimo,
	    int maximo,
	    Date fechaCreacion,
	    Date fechaActualizacion,
	    UnidadMedida unidadMedida,
	    Marca marca,
	    long idCategoria, // Aquí incluimos solo el ID de la categoría
	    String nombreCategoria // Incluye también el nombre de la categoría
	) {
	    public ProductoSimplificadoDto(Producto producto) {
	        this(
	            producto.getIdProducto(),
	            producto.getSku(),
	            producto.getNombreProducto(),
	            producto.getDescripcionProducto(),
	            producto.getPrecio(),
	            producto.getCantidad(),
	            producto.getMinimo(),
	            producto.getMaximo(),
	            producto.getFecha_creacion(),
	            producto.getFecha_actualizacion(),
	            producto.getUnidadMedida(),
	            producto.getMarca(),
	            producto.getCategoria() != null ? producto.getCategoria().getIdCategoria() : 0, // Verificamos si existe la categoría
	            producto.getCategoria() != null ? producto.getCategoria().getNombreCategoria() : null
	        );
	    }
	}
