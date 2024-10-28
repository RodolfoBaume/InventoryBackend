package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipoProducto")
public class TipoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_producto")
	private long idTipoProducto;
	private String tipoProducto;
	private Boolean status;
	
	public TipoProducto() {
		super();
	}

	public TipoProducto(long idTipoProducto, String tipoProducto, Boolean status) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.tipoProducto = tipoProducto;
		this.status = status;
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
	
}
