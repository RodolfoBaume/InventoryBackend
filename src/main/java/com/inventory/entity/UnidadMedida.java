package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="unidadesMedida")
public class UnidadMedida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidad_medida")
	private long idUnidadMedida;
	private String unidadMedida;
	private String simbolo;
	
	
	public UnidadMedida() {
		super();
	}

	public UnidadMedida(long idUnidadMedida, String unidadMedida, String simbolo) {
		super();
		this.idUnidadMedida = idUnidadMedida;
		this.unidadMedida = unidadMedida;
		this.simbolo = simbolo;
	}


	public long getIdUnidadMedida() {
		return idUnidadMedida;
	}

	public void setIdUnidadMedida(long idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	
}
