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

import com.inventory.dto.UbicacionDto;
import com.inventory.entity.Ubicacion;
import com.inventory.service.IUbicacionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class UbicacionController {

	@Autowired
	private IUbicacionService ubicacionService;

	// Consulta todos
	@GetMapping("/ubicaciones")
	@ResponseStatus(HttpStatus.OK)
	public List<Ubicacion> consulta() {
		return ubicacionService.findAll();
	}

	// Consulta paginación
	@GetMapping("/ubicaciones/page/{page}")
	public Page<Ubicacion> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idUbicacion").ascending());
		return ubicacionService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/ubicaciones/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		Ubicacion ubicacion = null;
		String response = "";
		try {
			ubicacion = ubicacionService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (ubicacion == null) {
			response = "La ubicacion con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ubicacion>(ubicacion, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/ubicaciones/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Ubicacion ubicacionDelete = this.ubicacionService.findById(id);
			if (ubicacionDelete == null) {
				response.put("mensaje", "Error al eliminar. La Ubicacion no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			ubicacionService.deleteUbicacion(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Ubicacion eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/ubicaciones")
	public ResponseEntity<?> create(@RequestBody UbicacionDto ubicacion) {
		Ubicacion ubicacionNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			ubicacionNew = this.ubicacionService.createUbicacion(ubicacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Ubicacion creada con éxito, con el ID " + ubicacionNew.getIdUbicacion());
		response.put("ubicacion", ubicacionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/ubicaciones/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody UbicacionDto ubicacion) {
		Ubicacion ubicacionNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			ubicacionNew = this.ubicacionService.updateUbicacion(id, ubicacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Ubicacion modificada con éxito, con el ID " + ubicacionNew.getIdUbicacion());
		response.put("ubicacion", ubicacionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
