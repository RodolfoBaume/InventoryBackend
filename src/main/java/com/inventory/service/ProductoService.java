package com.inventory.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.ProductoDto;
import com.inventory.entity.Producto;
import com.inventory.repository.IProductoRepository;

/**
 * Implementación del servicio para la entidad {@link Producto}.
 * Contiene la lógica de negocio para gestionar las operaciones CRUD de Producto.
 */
@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private IProductoRepository productoRepository;

	/**
     * Consulta todos los almacenes ordenados por id.
     * 
     * @return lista de todos los productos
     */
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll(Sort.by("idProducto"));
	}

	/**
     * Consulta todos los productos con soporte de paginación.
     * 
     * @param pageable objeto de paginación
     * @return página de productos
     */
	@Transactional(readOnly = true)
	public Page<Producto> findAllPage(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	/**
     * Busca un producto por su id.
     * 
     * @param idProducto id del producto a buscar
     * @return el prodcuto si existe, de lo contrario null
     */
	@Transactional(readOnly = true)
	public Producto findById(Long idProducto) {
		return productoRepository.findById(idProducto).orElse(null);
	}

	/**
     * Crea un nuevo producto a partir de un objeto ProductoDto.
     * 
     * @param producto DTO con los datos del producto a crear
     * @return el producto creado
     */
	@Transactional
	public Producto createProducto(ProductoDto producto) {
		Producto productoEntity = new Producto();
		productoEntity.setSku(producto.sku());
		productoEntity.setNombreProducto(producto.nombreProducto());
		productoEntity.setDescripcionProducto(producto.descripcionProducto());
		productoEntity.setPrecio(producto.precio());
		productoEntity.setCantidad(0); // producto nuevo - cantidad cero
		productoEntity.setFecha_creacion(new Date()); // Fecha del día
		productoEntity.setMinimo(producto.minimo());
		productoEntity.setMaximo(producto.maximo());
		productoEntity.setUnidadMedida(producto.unidadMedida());
		productoEntity.setMarca(producto.marca());
		productoEntity.setCategoria(producto.categoria());
		productoEntity.setGrupo(producto.grupo());
		productoEntity.setProveedor(producto.proveedor());
		return productoRepository.save(productoEntity);
	}

	/**
     * Elimina un producto por su id.
     * 
     * @param idProducto id del producto a eliminar
     * @return el producto eliminado o null si no existe
     */
	public Producto deleteProducto(Long idProducto) {
		productoRepository.deleteById(idProducto);
		return null;
	}

	/**
     * Actualiza un producto existente a partir de un DTO y su id.
     * 
     * @param idProducto id del producto a actualizar
     * @param producto DTO con los nuevos datos del producto
     * @return el producto actualizado
     */
	@Transactional
	public Producto updateProducto(Long idProducto, ProductoDto producto) {
		Producto productoEntity = productoRepository.findById(idProducto)
				.orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + idProducto));
		productoEntity.setSku(producto.sku());
		productoEntity.setNombreProducto(producto.nombreProducto());
		productoEntity.setDescripcionProducto(producto.descripcionProducto());
		productoEntity.setPrecio(producto.precio());
		// productoEntity.setCantidad(0); // cantidad no actualizar
		productoEntity.setFecha_actualizacion(new Date()); // Fecha del día para actualización
		productoEntity.setMinimo(producto.minimo());
		productoEntity.setMaximo(producto.maximo());
		productoEntity.setUnidadMedida(producto.unidadMedida());
		productoEntity.setMarca(producto.marca());
		productoEntity.setCategoria(producto.categoria());
		productoEntity.setGrupo(producto.grupo());
		productoEntity.setProveedor(producto.proveedor());
		return productoRepository.save(productoEntity);
	}
}
