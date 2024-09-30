package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ubicaciones")
public class Ubicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ubicacion")
	private long idUbicacion;
	private String nombreUbicacion;
	private String direccionUbicacion;
	
	
	public Ubicacion() {
		super();
	}

	public Ubicacion(long idUbicacion, String nombreUbicacion, String direccionUbicacion) {
		super();
		this.idUbicacion = idUbicacion;
		this.nombreUbicacion = nombreUbicacion;
		this.direccionUbicacion = direccionUbicacion;
	}

	public long getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public String getNombreUbicacion() {
		return nombreUbicacion;
	}

	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}

	public String getDireccionUbicacion() {
		return direccionUbicacion;
	}

	public void setDireccionUbicacion(String direccionUbicacion) {
		this.direccionUbicacion = direccionUbicacion;
	}
	
}
