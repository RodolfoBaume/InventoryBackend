package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inventarios")
public class InventarioProductoAtributos {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "valor_atributo_id")
    private ValorAtributo valorAtributo;

    @Column(nullable = false)
    private int cantidad;
    
    @ManyToOne
    @JoinColumn(name="almacen_id")
    private Almacen almacen;

	public InventarioProductoAtributos() {
		super();
	}

	public InventarioProductoAtributos(Long idInventario, Producto producto, ValorAtributo valorAtributo, int cantidad,
			Almacen almacen) {
		super();
		this.idInventario = idInventario;
		this.producto = producto;
		this.valorAtributo = valorAtributo;
		this.cantidad = cantidad;
		this.almacen = almacen;
	}

	public Long getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ValorAtributo getValorAtributo() {
		return valorAtributo;
	}

	public void setValorAtributo(ValorAtributo valorAtributo) {
		this.valorAtributo = valorAtributo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
    
}
