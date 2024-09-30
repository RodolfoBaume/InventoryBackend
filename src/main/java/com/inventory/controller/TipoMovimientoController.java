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

import com.inventory.dto.TipoMovimientoDto;
import com.inventory.entity.TipoMovimiento;
import com.inventory.service.ITipoMovimientoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class TipoMovimientoController {

	@Autowired
	private ITipoMovimientoService tipoMovimientoService;

	// Consulta todos
	@GetMapping("/tipoMovimientos")
	@ResponseStatus(HttpStatus.OK)
	public List<TipoMovimiento> consulta() {
		return tipoMovimientoService.findAll();
	}

	// Consulta paginación
	@GetMapping("/tipoMovimientos/page/{page}")
	public Page<TipoMovimiento> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idTipoMovimiento").ascending());
		return tipoMovimientoService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/tipoMovimientos/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		TipoMovimiento tipoMovimiento = null;
		String response = "";
		try {
			tipoMovimiento = tipoMovimientoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tipoMovimiento == null) {
			response = "El tipo de Movimiento con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoMovimiento>(tipoMovimiento, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/tipoMovimientos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			TipoMovimiento tipoMovimientoDelete = this.tipoMovimientoService.findById(id);
			if (tipoMovimientoDelete == null) {
				response.put("mensaje", "Error al eliminar. El Tipo de Movimiento no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			tipoMovimientoService.deleteTipoMovimiento(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Tipo de Movimiento eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/tipoMovimientos")
	public ResponseEntity<?> create(@RequestBody TipoMovimientoDto tipoMovimiento) {
		TipoMovimiento tipoMovimientoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tipoMovimientoNew = this.tipoMovimientoService.createTipoMovimiento(tipoMovimiento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Tipo de Movimiento creado con éxito, con el ID " + tipoMovimientoNew.getIdTipoMovimiento());
		response.put("tipoMovimiento", tipoMovimientoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/tipoMovimientos/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody TipoMovimientoDto tipoMovimiento) {
		TipoMovimiento tipoMovimientoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tipoMovimientoNew = this.tipoMovimientoService.updateTipoMovimiento(id, tipoMovimiento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Tipo de Movimiento modificado con éxito, con el ID " + tipoMovimientoNew.getIdTipoMovimiento());
		response.put("tipoMovimiento", tipoMovimientoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
