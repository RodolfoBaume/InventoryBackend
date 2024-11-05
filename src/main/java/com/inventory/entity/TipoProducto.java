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
@Table(name="tiposProducto")
public class TipoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_producto")
	private long idTipoProducto;
	private String tipoProducto;
	private Boolean status;
	
	@ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
	
	public TipoProducto() {
		super();
	}

	public TipoProducto(long idTipoProducto, String tipoProducto, Boolean status, Categoria categoria) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.tipoProducto = tipoProducto;
		this.status = status;
		this.categoria = categoria;
	}

	public long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Boolean getStatus() {
		return status;
	}
	
}
