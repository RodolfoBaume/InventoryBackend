package com.inventory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.ProductoDto;
import com.inventory.dto.ProductoSimplificadoDto;
import com.inventory.entity.Producto;
import com.inventory.service.IProductoService;

import jakarta.persistence.EntityNotFoundException;

/**
 * Controlador REST para gestionar las operaciones de la entidad {@link Producto}.
 * Proporciona endpoints para CRUD y búsquedas.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	/**
     * Consulta todos los productos.
     * 
     * @return una lista de productos.
     */
	@GetMapping("/productos")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ProductoSimplificadoDto>> obtenerTodosLosProductos() {
        List<ProductoSimplificadoDto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }
	/*
	public List<Producto> consulta() {
		return productoService.findAll();
	}
	*/

	/**
     * Consulta paginada de productos.
     * 
     * @param page número de página a consultar.
     * @return una página con productos.
     */
	@GetMapping("/productos/page/{page}")
	public Page<Producto> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idProducto").ascending());
		return productoService.findAllPage(pageable);
	}

	/**
     * Consulta un producto por su ID.
     * 
     * @param id ID del producto.
     * @return el producto si existe, error si no.
     */
	@GetMapping("/productos/{idProducto}")
	 public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long idProducto) {
        try {
            ProductoSimplificadoDto productoDto = productoService.obtenerProductoPorId(idProducto);
            return ResponseEntity.ok(productoDto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Producto no encontrado", "idProducto", idProducto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error inesperado", "detalle", ex.getMessage()));
        }
    }
	
	
	/*
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		Producto producto = null;
		String response = "";
		try {
			producto = productoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (producto == null) {
			response = "El producto con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

*/
	/**
     * Elimina un producto por su ID.
     * 
     * @param id ID del producto a eliminar.
     * @return mensaje de éxito o error.
     */
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Producto productoDelete = this.productoService.findById(id);
			if (productoDelete == null) {
				response.put("mensaje", "Error al eliminar. El producto no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			productoService.deleteProducto(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Producto eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
     * Crea un nuevo producto.
     * 
     * @param producto DTO con datos del nuevo producto.
     * @return el producto creado.
     */
	@PostMapping("/productos2")
	public ResponseEntity<?> create(@RequestBody ProductoDto producto) {
		Producto productoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			productoNew = this.productoService.createProducto2(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Producto creado con éxito, con el ID " + productoNew.getIdProducto());
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Crea nuevo producto
	@PostMapping("/productos")
    public ResponseEntity<Producto> createProducto(
            @RequestBody Producto producto,  
            @RequestParam Long combinacionAtributosId,
            @RequestParam int cantidadInicial) {
        
        Producto createdProducto = productoService.createProducto(producto, combinacionAtributosId, cantidadInicial);
        
        if (createdProducto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
    }
	

	/**
     * Actualiza un producto existente.
     * 
     * @param id ID del producto.
     * @param producto DTO con datos actualizados.
     * @return producto actualizado o error si no existe.
     */
	@PutMapping("/productos/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody ProductoDto producto) {
		Producto productoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			productoNew = this.productoService.updateProducto(id, producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Producto modificado con éxito, con el ID " + productoNew.getIdProducto());
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
