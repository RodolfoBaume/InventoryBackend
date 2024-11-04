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

import com.inventory.dto.MarcaDto;
import com.inventory.entity.Marca;
import com.inventory.service.IMarcaService;

/**
 * Controlador REST para gestionar las operaciones de la entidad {@link Marca}.
 * Proporciona endpoints para CRUD y búsquedas.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api")
public class MarcaController {

    @Autowired
    private IMarcaService marcaService;

    /**
     * Consulta todas las marcas.
     * 
     * @return una lista de marcas.
     */
    @GetMapping("/marcas")
    @ResponseStatus(HttpStatus.OK)
    public List<Marca> consulta() {
        return marcaService.findAll();
    }

    /**
     * Consulta paginada de marcas.
     * 
     * @param page número de página a consultar.
     * @return una página con marcas.
     */
    @GetMapping("/marcas/page/{page}")
    public Page<Marca> consultaPage(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("idMarca").ascending());
        return marcaService.findAllPage(pageable);
    }

    /**
     * Consulta una marca por su ID.
     * 
     * @param id ID de la marca.
     * @return la marca si existe, error si no.
     */
    @GetMapping("/marcas/{id}")
    public ResponseEntity<?> consultaPorID(@PathVariable Long id) {
        Marca marca = marcaService.findById(id);
        if (marca == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La marca con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(marca);
    }

    /**
     * Elimina una marca por su ID.
     * 
     * @param id ID de la marca a eliminar.
     * @return mensaje de éxito o error.
     */  
    @DeleteMapping("/marcas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Marca marcaDelete = this.marcaService.findById(id);
			if (marcaDelete == null) {
				response.put("mensaje", "Error al eliminar. La marca no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			marcaService.deleteMarca(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Marca eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

    /**
     * Crea una nueva marca.
     * 
     * @param marca DTO con datos de la nueva marca.
     * @return la marca creada.
     */
    @PostMapping("/marcas")
	public ResponseEntity<?> create(@RequestBody MarcaDto marca) {
		Marca marcaNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			marcaNew = this.marcaService.createMarca(marca);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Marca creada con éxito, con el ID " + marcaNew.getIdMarca());
		response.put("marca", marcaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

    /**
     * Actualiza una marca existente.
     * 
     * @param id ID de la marca.
     * @param marca DTO con datos actualizados.
     * @return marca actualizada o error si no existe.
     */
    @PutMapping("/marcas/{id}")
	public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody MarcaDto marca) {
		Marca marcaNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			marcaNew = this.marcaService.updateMarca(id, marca);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el update en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Marca modificada con éxito, con el ID " + marcaNew.getIdMarca());
		response.put("marca", marcaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}