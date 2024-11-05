package com.inventory.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Representa una categoría de productos en el sistema de inventario.
 * Esta entidad contiene la información principal de la categoría de productos,
 * como su nombre, descripción y sus subcategorías.
 * También mantiene una relación recursiva para la estructura jerárquica de subcategorías.
 * 
 * @author RBaume
 */
@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long idCategoria;

    private String nombreCategoria;
    private String descripcionCategoria;
    
    @Column(name = "folder")
    private Boolean folder;

    /**
     * Nodo padre de esta categoría en la estructura de categorías.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id_categoria")
    private Categoria parent;

    /**
     * Lista de subcategorías (hijos) que dependen de esta categoría.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categoria> subcategorias = new ArrayList<>();

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<TipoProducto> tiposProducto;
    
    
    /**
     * Constructor vacío necesario para JPA.
     */
    public Categoria() {
        super();
    }

    /**
     * Constructor que permite crear una categoría con todos sus atributos.
     *
     * @param idCategoria Identificador único de la categoría
     * @param nombreCategoria Nombre de la categoría
     * @param descripcionCategoria Descripción de la categoría
     * @param folder Indicador si es una carpeta para subcategorías
     * @param parent Nodo padre en el árbol de categorías
     * @param subcategorias Lista de subcategorías
     */
    public Categoria(long idCategoria, String nombreCategoria, String descripcionCategoria, Boolean folder,
                     Categoria parent, List<Categoria> subcategorias) {
        super();
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
        this.folder = folder;
        this.parent = parent;
        this.subcategorias = subcategorias;
    }

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return id de la categoría
     */
    public long getIdCategoria() {
        return idCategoria;
    }

    /**
     * Asigna el identificador de la categoría.
     *
     * @param idCategoria id de la categoría
     */
    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return nombre de la categoría
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Asigna el nombre de la categoría.
     *
     * @param nombreCategoria nombre de la categoría
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return descripción de la categoría
     */
    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    /**
     * Asigna la descripción de la categoría.
     *
     * @param descripcionCategoria descripción de la categoría
     */
    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    /**
     * Obtiene el valor de la propiedad folder.
     *
     * @return true si es carpeta, false en caso contrario
     */
    public Boolean getFolder() {
        return folder;
    }

    /**
     * Asigna el valor de la propiedad folder.
     *
     * @param folder true si es carpeta, false en caso contrario
     */
    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    /**
     * Obtiene la categoría padre.
     *
     * @return categoría padre
     */
    public Categoria getParent() {
        return parent;
    }

    /**
     * Asigna la categoría padre.
     *
     * @param parent categoría padre
     */
    public void setParent(Categoria parent) {
        this.parent = parent;
    }

    /**
     * Obtiene la lista de subcategorías.
     *
     * @return lista de subcategorías
     */
    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    /**
     * Asigna la lista de subcategorías.
     *
     * @param subcategorias lista de subcategorías
     */
    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

}
