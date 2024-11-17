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

import com.inventory.dto.ProveedorDto;
import com.inventory.entity.Proveedor;
import com.inventory.service.IProveedorService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class ProveedorController {

	@Autowired
	private IProveedorService proveedorService;

	// Consulta todos
	@GetMapping("/proveedores")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Proveedor>> findAll(@RequestParam(required = false, defaultValue = "false")boolean isDeleted) {
		List<Proveedor> proveedores = proveedorService.findAll(isDeleted);
		return new ResponseEntity<>(proveedores, HttpStatus.OK);
	}
	/*
	public List<Proveedor> consulta() {
		return proveedorService.findAll();
	}
	*/

	// Consulta paginación
	@GetMapping("/proveedores/page/{page}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Proveedor>> consultaPage(
	        @PathVariable Integer page,
	        @RequestParam(required = false, defaultValue = "false") boolean isDeleted) {
	    Pageable pageable = PageRequest.of(page, 10, Sort.by("idProveedor").ascending());
	    Page<Proveedor> proveedores = proveedorService.findAllPage(pageable, isDeleted);
	    return new ResponseEntity<>(proveedores, HttpStatus.OK);
	}

	// Consulta por id
	@GetMapping("/proveedores/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> consultaPorID(
	        @PathVariable Long id,
	        @RequestParam(required = false, defaultValue = "false") boolean isDeleted) {
	    Proveedor proveedor = null;
	    String response = "";
	    try {
	        proveedor = proveedorService.findById(id, isDeleted);
	    } catch (DataAccessException e) {
	        response = "Error al realizar la consulta.";
	        response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (proveedor == null) {
	        response = "El proveedor con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(proveedor, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<?> delete(
	        @PathVariable Long id, 
	        @RequestParam(required = false, defaultValue = "false") boolean isDeleted) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        // Buscar el proveedor considerando el estado del filtro (isDeleted)
	        Proveedor proveedorDelete = this.proveedorService.findById(id, isDeleted);

	        if (proveedorDelete == null) {
	            response.put("mensaje", "Error al eliminar. El proveedor no existe en la base de datos.");
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }

	        // Eliminar el proveedor (soft delete)
	        proveedorService.deleteProveedor(id);

	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al eliminar en la base de datos.");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getLocalizedMessage()));
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    response.put("mensaje", "Proveedor eliminado con éxito.");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/proveedores")
	public ResponseEntity<?> create(@RequestBody ProveedorDto proveedor) {
		Proveedor proveedorNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			proveedorNew = this.proveedorService.createProveedor(proveedor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Proveedor creado con éxito, con el ID " + proveedorNew.getIdProveedor());
		response.put("proveedor", proveedorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody ProveedorDto proveedor) {
		Proveedor proveedorNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			proveedorNew = this.proveedorService.updateProveedor(id, proveedor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Proveedor modificado con éxito, con el ID " + proveedorNew.getIdProveedor());
		response.put("proveedor", proveedorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
