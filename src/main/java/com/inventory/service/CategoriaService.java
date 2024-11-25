package com.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.CategoriaDto;
import com.inventory.dto.CategoriaProductoDto;
import com.inventory.entity.Categoria;
import com.inventory.repository.ICategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements ICategoriaService{

	@Autowired
	private ICategoriaRepository categoriaRepository;
	

	// Método para obtener todas las categorías en forma de árbol
    public List<CategoriaDto> obtenerCategoriasEnArbol() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return construirArbol(null, categorias);
    }
    
 // Método para obtener una categoría con subcategorías
    public CategoriaDto obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return mapearEntidadADTO(categoria);
    }

    // Método para guardar una categoría
    public CategoriaDto crearCategoria(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        mapearDTOaEntidad(categoriaDto, categoria);
        
        if (categoriaDto.parentId() != null) {
            Categoria parentCategoria = categoriaRepository.findById(categoriaDto.parentId())
                .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            categoria.setParent(parentCategoria);
        }
        
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return mapearEntidadADTO(categoriaGuardada);
    }
    
    //Crear categoria pasando toda la subcategoria
    public CategoriaDto crearCategoria2(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        mapearDTOaEntidad(categoriaDto, categoria);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return mapearEntidadADTO(categoriaGuardada);
    }

    // Método para actualizar una categoría
    public CategoriaDto actualizarCategoria(Long id, CategoriaDto categoriaDTO) {
        // Buscar la categoría existente por su ID
        Categoria categoriaExistente = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Actualizar los campos de la entidad existente con los valores del DTO
        categoriaExistente.setNombreCategoria(categoriaDTO.nombreCategoria());
        categoriaExistente.setDescripcionCategoria(categoriaDTO.descripcionCategoria());
        categoriaExistente.setFolder(categoriaDTO.folder());

        // Asignar el nodo padre si el parentId está presente
        if (categoriaDTO.parentId() != null) {
            Categoria parentCategoria = categoriaRepository.findById(categoriaDTO.parentId())
                .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            categoriaExistente.setParent(parentCategoria);
        } else {
            // Si parentId es null, quitar el padre (para desasociar)
            categoriaExistente.setParent(null);
        }

        // Guardar la categoría actualizada
        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);

        return mapearEntidadADTO(categoriaActualizada);
    }

    
    // pasando todos los parametros
    public CategoriaDto actualizarCategoria2(Long id, CategoriaDto categoriaDto) {
        Categoria categoriaExistente = categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria no encontrada"));
        mapearDTOaEntidad(categoriaDto, categoriaExistente);
        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
        return mapearEntidadADTO(categoriaActualizada);
    }

    // Método para eliminar una categoría
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Métodos de ayuda
    private List<CategoriaDto> construirArbol(Long parentId, List<Categoria> categorias) {
        return categorias.stream()
            .filter(categoria -> {
                Categoria parent = categoria.getParent();
                return (parentId == null && parent == null) ||
                       (parentId != null && parent != null && parentId.equals(parent.getIdCategoria()));
            })
            .map(this::mapearEntidadADTO)
            .collect(Collectors.toList());
    }


    private CategoriaDto mapearEntidadADTO(Categoria categoria) {
        // Usamos el constructor del record CategoriaDto para mapear la entidad a DTO
        return new CategoriaDto(
            categoria.getIdCategoria(),
            categoria.getNombreCategoria(),
            categoria.getDescripcionCategoria(),
            categoria.getFolder(),
            categoria.getParent() != null ? categoria.getParent().getIdCategoria() : null,
            construirArbol(categoria.getIdCategoria(), categoriaRepository.findAll())
        );
    }


    private void mapearDTOaEntidad(CategoriaDto dto, Categoria categoria) {
        categoria.setNombreCategoria(dto.nombreCategoria());
        categoria.setDescripcionCategoria(dto.descripcionCategoria());
        categoria.setFolder(dto.folder());
        if (dto.subcategorias() != null && !dto.subcategorias().isEmpty()) {
            List<Categoria> subcategorias = dto.subcategorias().stream()
                .map(subDto -> {
                    Categoria subCategoria = new Categoria();
                    mapearDTOaEntidad(subDto, subCategoria);
                    subCategoria.setParent(categoria);
                    return subCategoria;
                })
                .collect(Collectors.toList());
            categoria.setSubcategorias(subcategorias);
        }
    }
    
    
    //
    /*
    public List<CategoriaProductoDTO> obtenerCategoriasConProductos() {
        List<Categoria> categorias = categoriaRepository.obtenerCategoriasConProductos();

        return categorias.stream().map(categoria -> 
            new CategoriaProductoDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getProductos().stream()
                	.map(producto -> new ProductoVistaDto(
                			producto.getIdProducto(),
                			producto.getSku(),
                			producto.getNombreProducto(),
                			producto.getDescripcionProducto(),
                			producto.getPrecio(),
                			producto.getCantidad(),
                			producto.getMinimo(),
                			producto.getMaximo(),
                			producto.getUnidadMedida() != null ? producto.getUnidadMedida().getUnidadMedida() : null 
                			))
                	.collect(Collectors.toList())
            )
        ).collect(Collectors.toList());
    }
    
   */
    
  
    //Consulta por id categoria
    /*
    public Optional<CategoriaProductoDTO> obtenerCategoriaConProductosPorId(Long idCategoria) {
        return categoriaRepository.obtenerCategoriaConProductosPorId(idCategoria)
            .map(categoria -> new CategoriaProductoDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria(),
                categoria.getProductos().stream()
                    .map(producto -> new ProductoVistaDto(
                        producto.getIdProducto(),
                        producto.getSku(),
                        producto.getNombreProducto(),
                        producto.getDescripcionProducto(),
                        producto.getPrecio(),
                        producto.getCantidad(),
                        producto.getMinimo(),
                        producto.getMaximo(),
            			producto.getUnidadMedida() != null ? producto.getUnidadMedida().getUnidadMedida() : null 
                    ))
                    .collect(Collectors.toList())
            ));
    }  
    
      */       
    
    public CategoriaProductoDto obtenerProductosPorCategoria(Long idCategoria) {
        Categoria categoria = categoriaRepository.findCategoriaWithProductos(idCategoria)
            .orElseThrow(() -> new EntityNotFoundException("Categoría con ID " + idCategoria + " no encontrada"));
        
        return new CategoriaProductoDto(categoria);
    }
    
    //breadcrumb
    @Transactional(readOnly = true)
    public List<CategoriaDto> obtenerJerarquiaPorId(Long idCategoria) {
        // Buscar la categoría actual
        Categoria categoriaActual = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        // Crear una lista para almacenar la jerarquía
        List<CategoriaDto> jerarquia = new ArrayList<>();

        // Recorrer los padres de la categoría hasta llegar a la raíz
        while (categoriaActual != null) {
            // Agregar la categoría actual al inicio de la lista
            jerarquia.add(0, new CategoriaDto(
                categoriaActual.getIdCategoria(),
                categoriaActual.getNombreCategoria(),
                categoriaActual.getDescripcionCategoria(),
                categoriaActual.getFolder(),
                categoriaActual.getParent() != null ? categoriaActual.getParent().getIdCategoria() : null,
                List.of() // No incluir subcategorías en el breadcrumb
            ));
            // Subir al padre
            categoriaActual = categoriaActual.getParent();
        }

        return jerarquia; // Devuelve solo la jerarquía lineal
    }
    
}
