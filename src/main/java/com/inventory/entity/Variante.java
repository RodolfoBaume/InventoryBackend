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
@Table(name = "variantes")
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variante")
    private Long idVariante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_combinacion_atributos", nullable = false)
    private CombinacionAtributos combinacionAtributos;

	public Variante() {
		super();
	}

	public Variante(Long idVariante, Producto producto, CombinacionAtributos combinacionAtributos) {
		super();
		this.idVariante = idVariante;
		this.producto = producto;
		this.combinacionAtributos = combinacionAtributos;
	}

	public Long getIdVariante() {
		return idVariante;
	}

	public void setIdVariante(Long idVariante) {
		this.idVariante = idVariante;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public CombinacionAtributos getCombinacionAtributos() {
		return combinacionAtributos;
	}

	public void setCombinacionAtributos(CombinacionAtributos combinacionAtributos) {
		this.combinacionAtributos = combinacionAtributos;
	}
    
}
