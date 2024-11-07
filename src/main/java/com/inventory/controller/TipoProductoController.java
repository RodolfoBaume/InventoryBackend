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

import com.inventory.dto.TipoProductoDto;
import com.inventory.entity.TipoProducto;
import com.inventory.service.ITipoProductoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class TipoProductoController {

	@Autowired
	private ITipoProductoService tipoProductoService;

	// Consulta todos
	@GetMapping("/tiposProducto")
	@ResponseStatus(HttpStatus.OK)
	public List<TipoProducto> consulta() {
		return tipoProductoService.findAll();
	}

	// Consulta paginación
	@GetMapping("/tiposProducto/page/{page}")
	public Page<TipoProducto> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idTipoProducto").ascending());
		return tipoProductoService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/tiposProducto/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		TipoProducto tipoProducto = null;
		String response = "";
		try {
			tipoProducto = tipoProductoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tipoProducto == null) {
			response = "El tipo de Producto con el ID: ".concat(id.toString())
					.concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoProducto>(tipoProducto, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/tiposProducto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			TipoProducto tipoProductoDelete = this.tipoProductoService.findById(id);
			if (tipoProductoDelete == null) {
				response.put("mensaje", "Error al eliminar. El Tipo de Producto no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			tipoProductoService.deleteTipoMovimiento(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Tipo de Producto eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/tiposProducto")
	public ResponseEntity<?> create(@RequestBody TipoProductoDto tipoProducto) {
		TipoProducto tipoProductoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tipoProductoNew = this.tipoProductoService.createTipoProducto(tipoProducto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje",
				"Tipo de Producto creado con éxito, con el ID " + tipoProductoNew.getIdTipoProducto());
		response.put("tipoProducto", tipoProductoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/tiposProducto/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody TipoProductoDto tipoProducto) {
		TipoProducto tipoProductoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tipoProductoNew = this.tipoProductoService.updateTipoProducto(id, tipoProducto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje",
				"Tipo de Producto modificado con éxito, con el ID " + tipoProductoNew.getIdTipoProducto());
		response.put("tipoProducto", tipoProductoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
