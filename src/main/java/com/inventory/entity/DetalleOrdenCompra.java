package com.inventory.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DetalleOrdenCompra {
	private long idDetalleOrdenCompra;
	private int cantidad;
	private double precioUnitario;	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordenCompraId")
	private OrdenCompra ordenCompra;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productoId")
	private Producto producto;
		
	public DetalleOrdenCompra() {
		super();
	}

	public DetalleOrdenCompra(long idDetalleOrdenCompra, int cantidad, double precioUnitario, OrdenCompra ordenCompra,
			Producto producto) {
		super();
		this.idDetalleOrdenCompra = idDetalleOrdenCompra;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.ordenCompra = ordenCompra;
		this.producto = producto;
	}

	public long getIdDetalleOrdenCompra() {
		return idDetalleOrdenCompra;
	}

	public void setIdDetalleOrdenCompra(long idDetalleOrdenCompra) {
		this.idDetalleOrdenCompra = idDetalleOrdenCompra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
