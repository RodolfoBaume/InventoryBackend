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

import com.inventory.dto.ValorAtributoDto;
import com.inventory.entity.ValorAtributo;
import com.inventory.service.IValorAtributoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class ValorAtributoController {

	@Autowired
	private IValorAtributoService valorAtributoService;

	// Consulta todos
	@GetMapping("/valoresAtributos")
	@ResponseStatus(HttpStatus.OK)
	public List<ValorAtributo> consulta() {
		return valorAtributoService.findAll();
	}

	// Consulta todos los valores atributos asociados a un atributo específico
	@GetMapping("/valoresAtributos/{idAtributo}/atributo")
	public List<ValorAtributo> consultaPorGrupo(Long idAtributo) {
		return valorAtributoService.getValoresAtributosByAtributo(idAtributo);
	}

	// Consulta paginación
	@GetMapping("/valoresAtributos/page/{page}")
	public Page<ValorAtributo> consultaPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by("idValorAtributo").ascending());
		return valorAtributoService.findAllPage(pageable);
	}

	// Consulta por id
	@GetMapping("/valoresAtributos/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id) {

		ValorAtributo valorAtributo = null;
		String response = "";
		try {
			valorAtributo = valorAtributoService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta.";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (valorAtributo == null) {
			response = "El valor atributo con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ValorAtributo>(valorAtributo, HttpStatus.OK);
	}

	// Eliminar por id
	@DeleteMapping("/valoresAtributos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			ValorAtributo valorAtributoDelete = this.valorAtributoService.findById(id);
			if (valorAtributoDelete == null) {
				response.put("mensaje", "Error al eliminar. El valor atributo no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			valorAtributoService.deleteValorAtributo(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Valor Atributo eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Crear
	@PostMapping("/valoresAtributos")
	public ResponseEntity<?> create(@RequestBody Long atributo, ValorAtributo valorAtributo) {
		ValorAtributo valorAtributoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			valorAtributoNew = this.valorAtributoService.createValorAtributo(atributo, valorAtributo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Valor Atributo creado con éxito, con el ID " + valorAtributoNew.getIdValorAtributo());
		response.put("valorAtributo", valorAtributoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Modificar
	@PutMapping("/valoresAtributos/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody ValorAtributoDto valorAtributo) {
		ValorAtributo valorAtributoNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			valorAtributoNew = this.valorAtributoService.updateValorAtributo(id, valorAtributo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Valor Atributo modificado con éxito, con el ID " + valorAtributoNew.getIdValorAtributo());
		response.put("valorAtributo", valorAtributoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
