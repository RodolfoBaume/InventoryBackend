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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.AlmacenDto;
import com.inventory.entity.Almacen;
import com.inventory.service.IAlmacenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class AlmacenController {

	@Autowired
	private IAlmacenService almacenService;

	// Consulta todos
	@GetMapping("/almacenes")
	@ResponseStatus(HttpStatus.OK)
	public List<Almacen> consulta() {
		return almacenService.findAll();
	}

	// Consulta paginación
	@GetMapping("/almacenes/page/{page}")
	public Page<Almacen> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idAlmacen").ascending());
		return almacenService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/almacenes/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		Almacen almacen = null;
		String response = "";
		try {
			almacen = almacenService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (almacen == null) {
			response = "El almacen con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Almacen>(almacen, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/almacenes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Almacen almacenDelete = this.almacenService.findById(id);
			if (almacenDelete == null) {
				response.put("mensaje", "Error al eliminar. El almacen no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			almacenService.deleteAlmacen(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Almacen eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/almacenes")
	public ResponseEntity<?> create(@RequestBody AlmacenDto almacen) {
		Almacen almacenNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			almacenNew = this.almacenService.createAlmacen(almacen);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Almacen creado con éxito, con el ID " + almacenNew.getIdAlmacen());
		response.put("almacen", almacenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/almacenes/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody AlmacenDto almacen) {
		Almacen almacenNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			almacenNew = this.almacenService.updateAlmacen(id, almacen);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Almacen modificado con éxito, con el ID " + almacenNew.getIdAlmacen());
		response.put("almacen", almacenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
