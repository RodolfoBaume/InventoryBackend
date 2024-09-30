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

import com.inventory.dto.EstatusOrdenDto;
import com.inventory.entity.EstatusOrden;
import com.inventory.service.IEstatusOrdenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class EstatusOrdenController {

	@Autowired
	private IEstatusOrdenService estatusOrdenService;

	// Consulta todos
	@GetMapping("/estatusOrdenes")
	@ResponseStatus(HttpStatus.OK)
	public List<EstatusOrden> consulta() {
		return estatusOrdenService.findAll();
	}

	// Consulta paginación
	@GetMapping("/estatusOrdenes/page/{page}")
	public Page<EstatusOrden> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idEstatusOrden").ascending());
		return estatusOrdenService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/estatusOrdenes/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		EstatusOrden estatusOrden = null;
		String response = "";
		try {
			estatusOrden = estatusOrdenService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (estatusOrden == null) {
			response = "El Estatus de la Orden de Compra con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EstatusOrden>(estatusOrden, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/estatusOrdenes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			EstatusOrden estatusOrdenDelete = this.estatusOrdenService.findById(id);
			if (estatusOrdenDelete == null) {
				response.put("mensaje", "Error al eliminar. El Estatus de la Orden de Compra no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			estatusOrdenService.deleteEstatusOrden(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Estatus de la Orden de Compra eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/estatusOrdenes")
	public ResponseEntity<?> create(@RequestBody EstatusOrdenDto estatusOrden) {
		EstatusOrden estatusOrdenNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			estatusOrdenNew = this.estatusOrdenService.createEstatusOrden(estatusOrden);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Estatus de la Orden de Compra creada con éxito, con el ID " + estatusOrdenNew.getIdEstatusOrden());
		response.put("estatusOrden", estatusOrdenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/estatusOrdenes/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody EstatusOrdenDto estatusOrden) {
		EstatusOrden estatusOrdenNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			estatusOrdenNew = this.estatusOrdenService.updateEstatusOrden(id, estatusOrden);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Estatus de la Orden de Compra modificada con éxito, con el ID " + estatusOrdenNew.getIdEstatusOrden());
		response.put("estatusOrden", estatusOrdenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
