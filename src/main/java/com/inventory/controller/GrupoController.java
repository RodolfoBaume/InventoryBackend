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

import com.inventory.dto.GrupoDto;
import com.inventory.entity.Grupo;
import com.inventory.service.IGrupoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class GrupoController {

	@Autowired
	private IGrupoService grupoService;

	// Consulta todos
	@GetMapping("/grupos")
	@ResponseStatus(HttpStatus.OK)
	public List<Grupo> consulta() {
		return grupoService.findAll();
	}

	// Consulta paginación
	@GetMapping("/grupos/page/{page}")
	public Page<Grupo> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idGrupo").ascending());
		return grupoService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/grupos/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		Grupo grupo = null;
		String response = "";
		try {
			grupo = grupoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (grupo == null) {
			response = "El grupo con el ID: ".concat(id.toString())
					.concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/grupos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Grupo grupoDelete = this.grupoService.findById(id);
			if (grupoDelete == null) {
				response.put("mensaje", "Error al eliminar. El Grupo no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			grupoService.deleteGrupo(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Grupo eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/grupos")
	public ResponseEntity<?> create(@RequestBody GrupoDto grupo) {
		Grupo grupoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			grupoNew = this.grupoService.createGrupo(grupo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje",
				"Grupo creado con éxito, con el ID " + grupoNew.getIdGrupo());
		response.put("grupo", grupoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/grupos/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody GrupoDto grupo) {
		Grupo grupoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			grupoNew = this.grupoService.updateGrupo(id, grupo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje",
				"Grupo modificado con éxito, con el ID " + grupoNew.getIdGrupo());
		response.put("grupo", grupoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
