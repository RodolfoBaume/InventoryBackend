package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa el estatus de una orden de compra en el sistema de inventario.
 * Esta entidad define los distintos estados que puede tener una orden de compra, tales como "Pendiente", "En proceso", "Completada", etc.
 * Cada estatus tiene un identificador único y un nombre descriptivo.
 * 
 * @autor RBaume
 */
@Entity
@Table(name="estatusOrdenes")
public class EstatusOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estatus_orden")
    private long idEstatusOrden;

    private String estatusOrden;

    /**
     * Constructor vacío necesario para JPA.
     */
    public EstatusOrden() {
        super();
    }

    /**
     * Constructor que permite crear un estatus de orden con todos sus atributos.
     *
     * @param idEstatusOrden Identificador único del estatus de la orden
     * @param estatusOrden Nombre descriptivo del estatus de la orden
     */
    public EstatusOrden(long idEstatusOrden, String estatusOrden) {
        super();
        this.idEstatusOrden = idEstatusOrden;
        this.estatusOrden = estatusOrden;
    }

    /**
     * Obtiene el identificador del estatus de la orden.
     *
     * @return id del estatus de la orden
     */
    public long getIdEstatusOrden() {
        return idEstatusOrden;
    }

    /**
     * Asigna el identificador del estatus de la orden.
     *
     * @param idEstatusOrden id del estatus de la orden
     */
    public void setIdEstatusOrden(long idEstatusOrden) {
        this.idEstatusOrden = idEstatusOrden;
    }

    /**
     * Obtiene el nombre descriptivo del estatus de la orden.
     *
     * @return nombre del estatus de la orden
     */
    public String getEstatusOrden() {
        return estatusOrden;
    }

    /**
     * Asigna el nombre descriptivo del estatus de la orden.
     *
     * @param estatusOrden nombre del estatus de la orden
     */
    public void setEstatusOrden(String estatusOrden) {
        this.estatusOrden = estatusOrden;
    }   
}
