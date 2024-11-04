package com.inventory.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa una orden de compra en el sistema de inventario.
 * Esta entidad contiene la información principal de una orden de compra, como la fecha de la orden,
 * el total de la compra, el estatus de la orden y el proveedor relacionado.
 * 
 * @autor RBaume
 */
@Entity
@Table(name="ordenesCompra")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_compra")
    private long idOrdenCompra;

    private Date fecha_orden;

    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estatusOrdenId")
    private EstatusOrden estatusOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedorId")
    private Proveedor proveedor;

    /**
     * Constructor vacío necesario para JPA.
     */
    public OrdenCompra() {
        super();
    }

    /**
     * Constructor que permite crear una orden de compra con todos sus atributos.
     *
     * @param idOrdenCompra Identificador único de la orden de compra
     * @param fecha_orden Fecha de la orden de compra
     * @param total Monto total de la orden de compra
     * @param estatusOrden Estatus actual de la orden de compra
     * @param proveedor Proveedor relacionado con la orden de compra
     */
    public OrdenCompra(long idOrdenCompra, Date fecha_orden, double total, EstatusOrden estatusOrden,
                       Proveedor proveedor) {
        super();
        this.idOrdenCompra = idOrdenCompra;
        this.fecha_orden = fecha_orden;
        this.total = total;
        this.estatusOrden = estatusOrden;
        this.proveedor = proveedor;
    }

    /**
     * Obtiene el identificador de la orden de compra.
     *
     * @return id de la orden de compra
     */
    public long getIdOrdenCompra() {
        return idOrdenCompra;
    }

    /**
     * Asigna el identificador de la orden de compra.
     *
     * @param idOrdenCompra id de la orden de compra
     */
    public void setIdOrdenCompra(long idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    /**
     * Obtiene la fecha de la orden de compra.
     *
     * @return fecha de la orden de compra
     */
    public Date getFecha_orden() {
        return fecha_orden;
    }

    /**
     * Asigna la fecha de la orden de compra.
     *
     * @param fecha_orden fecha de la orden de compra
     */
    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    /**
     * Obtiene el monto total de la orden de compra.
     *
     * @return total de la orden de compra
     */
    public double getTotal() {
        return total;
    }

    /**
     * Asigna el monto total de la orden de compra.
     *
     * @param total total de la orden de compra
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el estatus actual de la orden de compra.
     *
     * @return estatus de la orden de compra
     */
    public EstatusOrden getEstatusOrden() {
        return estatusOrden;
    }

    /**
     * Asigna el estatus de la orden de compra.
     *
     * @param estatusOrden estatus de la orden de compra
     */
    public void setEstatusOrden(EstatusOrden estatusOrden) {
        this.estatusOrden = estatusOrden;
    }

    /**
     * Obtiene el proveedor relacionado con la orden de compra.
     *
     * @return proveedor de la orden de compra
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Asigna el proveedor de la orden de compra.
     *
     * @param proveedor proveedor de la orden de compra
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
