package com.inventory.dto;

import com.inventory.entity.Marca;

/**
 * DTO para la entidad {@link Marca}.
 * Se utiliza para transferir datos de Marca entre capas de la aplicación.
 * 
 * Atributos:
 * - {@link #idMarca}: Identificador de la marca.
 * - {@link #marca}: Nombre de la marca.
 */
public record MarcaDto(
        long idMarca,
        String marca
) {

    /**
     * Constructor que permite crear una instancia de MarcaDto desde una entidad Marca.
     * 
     * @param marca la entidad Marca de la cual se copiarán los datos.
     */
    public MarcaDto(Marca marca) {
        this(marca.getIdMarca(), marca.getMarca());
    }
}
