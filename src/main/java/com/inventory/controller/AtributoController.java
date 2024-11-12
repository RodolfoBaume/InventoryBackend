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

import com.inventory.dto.AtributoDto;
import com.inventory.entity.Atributo;
import com.inventory.service.IAtributoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class AtributoController {

	@Autowired
	private IAtributoService atributoService;

	// Consulta todos
	@GetMapping("/atributos")
	@ResponseStatus(HttpStatus.OK)
	public List<Atributo> consulta() {
		return atributoService.findAll();
	}
	
	// Consulta todos los atributos asociados a un grupo específico
	@GetMapping("/atributos/{idGrupo}/grupo")
	public List<Atributo> consultaPorGrupo(Long idGrupo){
		return atributoService.getAtributosByGrupo(idGrupo);
	}
	
	// Consulta paginación
	@GetMapping("/atributos/page/{page}")
	public Page<Atributo> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idAlmacen").ascending());
		return atributoService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/atributos/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		Atributo atributo = null;
		String response = "";
		try {
			atributo = atributoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (atributo == null) {
			response = "El atributo con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Atributo>(atributo, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/atributos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Atributo atributoDelete = this.atributoService.findById(id);
			if (atributoDelete == null) {
				response.put("mensaje", "Error al eliminar. El atributo no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			atributoService.deleteAtributo(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Atributo eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/atributos")
	public ResponseEntity<?> create(@RequestBody Long grupo,Atributo atributo) {
		Atributo atributoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			atributoNew = this.atributoService.createAtributo(grupo, atributo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Atributo creado con éxito, con el ID " + atributoNew.getIdAtributo());
		response.put("atributo", atributoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/atributos/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody AtributoDto atributo) {
		Atributo atributoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			atributoNew = this.atributoService.updateAtributo(id, atributo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Atributo modificado con éxito, con el ID " + atributoNew.getIdAtributo());
		response.put("atributo", atributoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
