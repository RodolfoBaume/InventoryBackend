package com.inventory.dto;

import java.util.Date;

import com.inventory.entity.Categoria;
import com.inventory.entity.Marca;
import com.inventory.entity.Producto;
import com.inventory.entity.Proveedor;
import com.inventory.entity.TipoProducto;
import com.inventory.entity.UnidadMedida;

/**
 * DTO para la transferencia de datos de la entidad {@link Producto}.
 */
public record ProductoDto(long idProducto, String sku, String nombreProducto, String descripcionProducto, double precio,
		int cantidad, Date fecha_creacion, Date fecha_actualizacion, int minimo, int maximo, UnidadMedida unidadMedida,
		Marca marca, TipoProducto tipoProducto, Categoria categoria, Proveedor proveedor) {
	/**
     * Constructor que inicializa un ProductoDto a partir de un objeto Producto.
     *
     * @param producto entidad Producto para crear el DTO
     */
	public ProductoDto(Producto producto) {
		this(producto.getIdProducto(), producto.getSku(), producto.getNombreProducto(), producto.getDescripcionProducto(), producto.getPrecio(), producto.getCantidad(), producto.getFecha_creacion(), producto.getFecha_actualizacion(), producto.getMinimo(), producto.getMaximo(), producto.getUnidadMedida(), producto.getMarca(), producto.getTipoProducto(),producto.getCategoria(), producto.getProveedor());
	}
}
