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
	private String telefono;
	private String responsable;
	
	
	public Ubicacion() {
		super();
	}

	public Ubicacion(long idUbicacion, String nombreUbicacion, String direccionUbicacion, String telefono,
			String responsable) {
		super();
		this.idUbicacion = idUbicacion;
		this.nombreUbicacion = nombreUbicacion;
		this.direccionUbicacion = direccionUbicacion;
		this.telefono = telefono;
		this.responsable = responsable;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
}
