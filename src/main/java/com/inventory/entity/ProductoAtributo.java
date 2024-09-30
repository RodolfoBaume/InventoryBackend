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
@Table(name="atributos_producto")
public class ProductoAtributo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atributo_producto")
	private long  idAtributoProducto;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productoId")
	private Producto producto;
	private String nombreAtributo;
	private String valorAtributo;
		
	public ProductoAtributo() {
		super();
	}

	public ProductoAtributo(long idAtributoProducto, Producto producto, String nombreAtributo, String valorAtributo) {
		super();
		this.idAtributoProducto = idAtributoProducto;
		this.producto = producto;
		this.nombreAtributo = nombreAtributo;
		this.valorAtributo = valorAtributo;
	}

	public long getIdAtributoProducto() {
		return idAtributoProducto;
	}

	public void setIdAtributoProducto(long idAtributoProducto) {
		this.idAtributoProducto = idAtributoProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}

	public String getValorAtributo() {
		return valorAtributo;
	}

	public void setValorAtributo(String valorAtributo) {
		this.valorAtributo = valorAtributo;
	}
		
}
