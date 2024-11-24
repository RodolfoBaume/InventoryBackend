package com.inventory.entity;

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
@Table(name="inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_variante", nullable = false)
    private Variante variante;

    private int cantidad;
    
    @ManyToOne
    @JoinColumn(name="almacen_id")
    private Almacen almacen;

	public Inventario() {
		super();
	}

	

	public Inventario(Long idInventario, Variante variante, int cantidad, Almacen almacen) {
		super();
		this.idInventario = idInventario;
		this.variante = variante;
		this.cantidad = cantidad;
		this.almacen = almacen;
	}



	public Long getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	public Variante getVariante() {
		return variante;
	}

	public void setVariante(Variante variante) {
		this.variante = variante;
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