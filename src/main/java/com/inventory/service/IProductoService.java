package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.ProductoDto;
import com.inventory.dto.ProductoSimplificadoDto;
import com.inventory.entity.Producto;

/**
 * Interface de servicio que define las operaciones para la entidad {@link Producto}.
 * Métodos incluidos:
 * - Búsqueda de todas los productos.
 * - Paginación.
 * - Creación, eliminación y actualización de productos.
 */
public interface IProductoService {
	/**
     * Consulta todas las productos.
     * 
     * @return una lista con todas las productos.
     */
    //List<Producto> findAll();
    List<ProductoSimplificadoDto> obtenerTodosLosProductos();

    /**
     * Consulta todas las productos con paginación.
     * 
     * @param pageable objeto de paginación que contiene página, tamaño, etc.
     * @return una página con productos.
     */
    Page<Producto> findAllPage(Pageable pageable);

    /**
     * Busca un producto por su ID.
     * 
     * @param idproducto ID del producto a buscar.
     * @return el producto encontrado o null si no existe.
     */
    Producto findById(Long idProducto);
    
    
    ProductoSimplificadoDto obtenerProductoPorId(Long idProducto);
    
    /**
     * Crea un nuevo producto.
     * 
     * @param producto DTO del producto a crear.
     * @return el producto creado.
     */
    Producto createProducto2(ProductoDto producto);
    
    Producto createProducto(Producto producto, Long combinacionAtributosId, int cantidadInicial);

    /**
     * Elimina un producto por su ID.
     * 
     * @param idproducto ID del producto a eliminar.
     * @return el producto eliminado o null si no existe.
     */
    Producto deleteProducto(Long idProducto);

    /**
     * Actualiza un producto existente.
     * 
     * @param idproducto ID del producto a actualizar.
     * @param producto DTO del producto con la información actualizada.
     * @return el producto actualizada.
     */
    Producto updateProducto(Long idProducto, ProductoDto producto);
}
