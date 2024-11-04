package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa un almacén en el sistema de inventario.
 * Esta entidad contiene la información principal del almacén, como su nombre, dirección, teléfono y responsable.
 * @author RBaume
 */
@Entity
@Table(name = "almacenes")
public class Almacen {
    
    /**
     * Identificador único del almacén.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private long idAlmacen;

    /**
     * Nombre del almacén.
     */
    @Column(name = "nombre_almacen")
    private String nombreAlmacen;

    /**
     * Dirección del almacén.
     */
    @Column(name = "direccion_almacen")
    private String direccionAlmacen;

    /**
     * Teléfono de contacto del almacén.
     */
    @Column(name = "telefono_almacen")
    private String telefonoAlmacen;

    /**
     * Nombre del responsable del almacén.
     */
    @Column(name = "responsable_almacen")
    private String responsableAlmacen;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Almacen() {}

    /**
     * Constructor que permite crear un almacén con todos sus atributos.
     *
     * @param idAlmacen Identificador único del almacén
     * @param nombreAlmacen Nombre del almacén
     * @param direccionAlmacen Dirección del almacén
     * @param telefonoAlmacen Teléfono de contacto del almacén
     * @param responsableAlmacen Responsable del almacén
     */
    public Almacen(long idAlmacen, String nombreAlmacen, String direccionAlmacen, String telefonoAlmacen, String responsableAlmacen) {
        this.idAlmacen = idAlmacen;
        this.nombreAlmacen = nombreAlmacen;
        this.direccionAlmacen = direccionAlmacen;
        this.telefonoAlmacen = telefonoAlmacen;
        this.responsableAlmacen = responsableAlmacen;
    }

    /**
     * Obtiene el identificador del almacén.
     *
     * @return id del almacén
     */
    public long getIdAlmacen() {
        return idAlmacen;
    }

    /**
     * Asigna el identificador del almacén.
     *
     * @param idAlmacen id del almacén
     */
    public void setIdAlmacen(long idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    /**
     * Obtiene el nombre del almacén.
     *
     * @return nombre del almacén
     */
    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    /**
     * Asigna el nombre del almacén.
     *
     * @param nombreAlmacen nombre del almacén
     */
    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    /**
     * Obtiene la dirección del almacén.
     *
     * @return dirección del almacén
     */
    public String getDireccionAlmacen() {
        return direccionAlmacen;
    }

    /**
     * Asigna la dirección del almacén.
     *
     * @param direccionAlmacen dirección del almacén
     */
    public void setDireccionAlmacen(String direccionAlmacen) {
        this.direccionAlmacen = direccionAlmacen;
    }

    /**
     * Obtiene el teléfono de contacto del almacén.
     *
     * @return teléfono del almacén
     */
    public String getTelefonoAlmacen() {
        return telefonoAlmacen;
    }

    /**
     * Asigna el teléfono de contacto del almacén.
     *
     * @param telefonoAlmacen teléfono del almacén
     */
    public void setTelefonoAlmacen(String telefonoAlmacen) {
        this.telefonoAlmacen = telefonoAlmacen;
    }

    /**
     * Obtiene el nombre del responsable del almacén.
     *
     * @return responsable del almacén
     */
    public String getResponsableAlmacen() {
        return responsableAlmacen;
    }

    /**
     * Asigna el nombre del responsable del almacén.
     *
     * @param responsableAlmacen responsable del almacén
     */
    public void setResponsableAlmacen(String responsableAlmacen) {
        this.responsableAlmacen = responsableAlmacen;
    }

    /**
     * Representación en cadena de texto de la entidad Almacen.
     *
     * @return una cadena con los valores de los atributos del almacén
     */
    @Override
    public String toString() {
        return "Almacen{" +
                "idAlmacen=" + idAlmacen +
                ", nombreAlmacen='" + nombreAlmacen + '\'' +
                ", direccionAlmacen='" + direccionAlmacen + '\'' +
                ", telefonoAlmacen='" + telefonoAlmacen + '\'' +
                ", responsableAlmacen='" + responsableAlmacen + '\'' +
                '}';
    }
}
