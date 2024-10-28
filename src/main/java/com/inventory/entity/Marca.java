package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa una marca en el sistema de inventario.
 * Esta entidad mapea la tabla 'marcas' en la base de datos y contiene información
 * relevante para identificar una marca.
 * 
 * Atributos de la entidad:
 * - {@link #idMarca}: ID único para la marca.
 * - {@link #marca}: Nombre de la marca.
 * 
 * Esta clase proporciona métodos para acceder y modificar sus atributos.
 */
@Entity
@Table(name = "marcas")
public class Marca {

    /**
     * ID único para cada marca.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private long idMarca;

    /**
     * Nombre de la marca.
     */
    private String marca;

    /**
     * Constructor sin argumentos.
     * Inicializa una instancia de {@code Marca} con valores por defecto.
     */
    public Marca() {
        super();
    }

    /**
     * Constructor con parámetros.
     * 
     * @param idMarca ID de la marca.
     * @param marca Nombre de la marca.
     */
    public Marca(long idMarca, String marca) {
        super();
        this.idMarca = idMarca;
        this.marca = marca;
    }

    /**
     * Obtiene el ID de la marca.
     * 
     * @return el ID de la marca.
     */
    public long getIdMarca() {
        return idMarca;
    }

    /**
     * Establece el ID de la marca.
     * 
     * @param idMarca el nuevo ID para la marca.
     */
    public void setIdMarca(long idMarca) {
        this.idMarca = idMarca;
    }

    /**
     * Obtiene el nombre de la marca.
     * 
     * @return el nombre de la marca.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece el nombre de la marca.
     * 
     * @param marca el nuevo nombre de la marca.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
}