package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.entity.CombinacionAtributos;
import com.inventory.service.ICombinacionAtributosService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class CombinacionAtributosController {
	@Autowired
    private ICombinacionAtributosService combinacionAtributosService;

    /**
     * Endpoint para crear una nueva combinación de atributos asociada a un grupo.
     * 
     * @param grupoId ID del grupo al que se asociará la combinación de atributos
     * @param valorAtributoIds Lista de IDs de los valores de atributos que se incluirán en la combinación
     * @return La combinación de atributos creada o un mensaje de error si el grupo no existe
     */
    @PostMapping("/combinacionesAtributos")
    public ResponseEntity<?> createCombinacion(
            @RequestParam Long grupoId, 
            @RequestBody List<Long> valorAtributoIds) {
        
        // Llamar al servicio para crear la combinación de atributos
        CombinacionAtributos combinacion = combinacionAtributosService.createCombinacion(grupoId, valorAtributoIds);
        
        if (combinacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado con el ID proporcionado.");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(combinacion);
    }
}