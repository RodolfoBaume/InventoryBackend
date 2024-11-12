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
import com.inventory.entity.CombinacionAtributos;
import com.inventory.entity.Inventario;
import com.inventory.entity.Producto;
import com.inventory.entity.Variante;
import com.inventory.repository.ICombinacionAtributosRepository;
import com.inventory.repository.IInventarioRepository;
import com.inventory.repository.IProductoRepository;
import com.inventory.repository.IVarianteRepository;

/**
 * Implementación del servicio para la entidad {@link Producto}. Contiene la
 * lógica de negocio para gestionar las operaciones CRUD de Producto.
 */
@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Autowired
	private ICombinacionAtributosRepository combinacionAtributosRepository;

	@Autowired
	private IVarianteRepository varianteRepository;

	@Autowired
	private IInventarioRepository inventarioRepository;

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
	public Producto createProducto2(ProductoDto producto) {
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
	 * Crea un nuevo producto, asigna una variante en base a la combinación de
	 * atributos y establece una cantidad inicial en inventario para esa variante.
	 * 
	 * @param producto               El producto a crear
	 * @param combinacionAtributosId ID de la combinación de atributos para la
	 *                               variante
	 * @param cantidadInicial        Cantidad inicial en inventario para la variante
	 * @return El producto creado o null si no se encontró la combinación de
	 *         atributos
	 */
	@Transactional
	public Producto createProducto(Producto producto, Long combinacionAtributosId, int cantidadInicial) {
		// Buscar la combinación de atributos por su ID
		CombinacionAtributos combinacion = combinacionAtributosRepository.findById(combinacionAtributosId).orElse(null);

		if (combinacion == null) {
			return null; // Retornar null si no se encontró la combinación
		}

		// Asignar el grupo al producto basado en la combinación de atributos
		producto.setGrupo(combinacion.getGrupo());
		Producto savedProducto = productoRepository.save(producto);

		// Crear y asociar una variante para el producto con la combinación de atributos
		Variante variante = new Variante();
		variante.setProducto(savedProducto);
		variante.setCombinacionAtributos(combinacion);
		Variante savedVariante = varianteRepository.save(variante);

		// Crear una entrada en el inventario para la variante con la cantidad inicial
		Inventario inventario = new Inventario();
		inventario.setVariante(savedVariante);
		inventario.setCantidad(cantidadInicial);
		inventarioRepository.save(inventario);

		return savedProducto;
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
	 * @param producto   DTO con los nuevos datos del producto
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
