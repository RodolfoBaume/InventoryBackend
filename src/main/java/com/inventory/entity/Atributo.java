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
@Table(name="atributos")
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atributoId;
    
    @Column(nullable = false)
    private String atributo;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
	public Atributo() {
		super();
	}

	public Atributo(Long atributoId, String atributo, Producto producto) {
		super();
		this.atributoId = atributoId;
		this.atributo = atributo;
		this.producto = producto;
	}

	public Long getAtributoId() {
		return atributoId;
	}

	public void setAtributoId(Long atributoId) {
		this.atributoId = atributoId;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
    
}

