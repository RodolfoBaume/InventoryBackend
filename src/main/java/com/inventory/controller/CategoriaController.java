package com.inventory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.CategoriaDto;
import com.inventory.dto.CategoriaProductoDto;
import com.inventory.projection.CategoriaProductoDTO;
import com.inventory.service.ICategoriaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/api")
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;
	

	// Endpoint para obtener todas las categorías en forma de árbol
    @GetMapping("/categorias")
    public List<CategoriaDto> obtenerCategoriasEnArbol() {
        return categoriaService.obtenerCategoriasEnArbol();
    }
    
 // Endpoint para obtener una categoría por su ID
    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaDto> obtenerCategoriaPorId(@PathVariable Long id) {
        CategoriaDto categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    // Endpoint para crear una nueva categoría
    @PostMapping("/categorias")
    public CategoriaDto crearCategoria(@RequestBody CategoriaDto categoriaDto) {
        return categoriaService.crearCategoria(categoriaDto);
    }
    

    // Endpoint para actualizar una categoría existente
    @PutMapping("/categorias/{id}")
    public CategoriaDto actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDto categoriaDto) {
        return categoriaService.actualizarCategoria(id, categoriaDto);
    }

    // Endpoint para eliminar una categoría
    @DeleteMapping("/categorias/{id}")
    public void eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
    }
    
    
    @GetMapping("/categorias/productos")
    public ResponseEntity<List<CategoriaProductoDTO>> obtenerCategoriasConProductos() {
        List<CategoriaProductoDTO> categoriasConProductos = categoriaService.obtenerCategoriasConProductos();
        return ResponseEntity.ok(categoriasConProductos);
    }
    
    
    
    @GetMapping("/categorias/{idCategoria}/productos")
    public ResponseEntity<?> obtenerProductosPorCategoria(@PathVariable Long idCategoria) {
        try {
            CategoriaProductoDto categoriaProductoDto = categoriaService.obtenerProductosPorCategoria(idCategoria);
            return ResponseEntity.ok(categoriaProductoDto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Categoría no encontrada", "idCategoria", idCategoria));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error inesperado", "detalle", ex.getMessage()));
        }
    }
}
