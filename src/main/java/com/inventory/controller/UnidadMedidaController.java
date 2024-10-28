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

import com.inventory.dto.UnidadMedidaDto;
import com.inventory.entity.UnidadMedida;
import com.inventory.service.IUnidadMedidaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class UnidadMedidaController {
	
	@Autowired
	private IUnidadMedidaService unidadMedidaService;
	
	// Consulta todos
		@GetMapping("/unidadesMedida")
		@ResponseStatus(HttpStatus.OK)
		public List<UnidadMedida> consulta() {
			return unidadMedidaService.findAll();
		}

		// Consulta paginación
		@GetMapping("/unidadesMedida/page/{page}")
		public Page<UnidadMedida> consultaPage(@PathVariable Integer page) {
			Pageable pageable = PageRequest.of(page, 10, Sort.by("idUnidadMedida").ascending());
			return unidadMedidaService.findAllPage(pageable);
		}

		// Consulta por id
		@GetMapping("/unidadesMedida/{id}")
		public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

			UnidadMedida unidadMedida = null;
			String response = "";
			try {
				unidadMedida = unidadMedidaService.findById(id);
			} catch (DataAccessException e) {
				response = "Error al realizar la consulta.";
				response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
				return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (unidadMedida == null) {
				response = "La Unidad de Medida con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<UnidadMedida>(unidadMedida, HttpStatus.OK);
		}

		// Eliminar por id
		@DeleteMapping("/unidadesMedida/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id) {

			Map<String, Object> response = new HashMap<>();

			try {
				UnidadMedida unidadMedidaDelete = this.unidadMedidaService.findById(id);
				if (unidadMedidaDelete == null) {
					response.put("mensaje", "Error al eliminar. La unidad de medida no existe en base de datos");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				unidadMedidaService.deleteUnidadMedida(id);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al eliminar en base de datos");
				response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "Unidad de Medida eliminada con éxito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		// Crear
		@PostMapping("/unidadesMedida")
		public ResponseEntity<?> create(@RequestBody UnidadMedidaDto unidadMedida) {
			UnidadMedida unidadMedidaNew = null;
			Map<String, Object> response = new HashMap<>();

			try {
				unidadMedidaNew = this.unidadMedidaService.createUnidadMedida(unidadMedida);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en base de datos");
				response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "Unidad de Medida creada con éxito, con el ID " + unidadMedidaNew.getIdUnidadMedida());
			response.put("unidadMedida", unidadMedidaNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

		// Modificar
		@PutMapping("/unidadesMedida/{id}")
		public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody UnidadMedidaDto unidadMedida) {
			UnidadMedida unidadMedidaNew = null;
			Map<String, Object> response = new HashMap<>();

			try {
				unidadMedidaNew = this.unidadMedidaService.updateUnidadMedida(id, unidadMedida);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el update en base de datos");
				response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "Unidad de Medida modificada con éxito, con el ID " + unidadMedidaNew.getIdUnidadMedida());
			response.put("unidadMedida", unidadMedidaNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

}
