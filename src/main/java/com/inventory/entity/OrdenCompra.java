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
	
	public OrdenCompra() {
		super();
	}

	public OrdenCompra(long idOrdenCompra, Date fecha_orden, double total, EstatusOrden estatusOrden,
			Proveedor proveedor) {
		super();
		this.idOrdenCompra = idOrdenCompra;
		this.fecha_orden = fecha_orden;
		this.total = total;
		this.estatusOrden = estatusOrden;
		this.proveedor = proveedor;
	}

	public long getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(long idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public Date getFecha_orden() {
		return fecha_orden;
	}

	public void setFecha_orden(Date fecha_orden) {
		this.fecha_orden = fecha_orden;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public EstatusOrden getEstatusOrden() {
		return estatusOrden;
	}

	public void setEstatusOrden(EstatusOrden estatusOrden) {
		this.estatusOrden = estatusOrden;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
