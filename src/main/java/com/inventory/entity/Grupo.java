package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="grupos")
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo")
	private long idGrupo;
	private String nombreGrupo;
	private Boolean status;
	
	public Grupo() {
		super();
	}

	public Grupo(long idGrupo, String nombreGrupo, Boolean status) {
		super();
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
		this.status = status;
	}

	public long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
