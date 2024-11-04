package com.inventory.entity;


import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="proveedores")
@SQLDelete(sql = "UPDATE proveedores SET deleted = true WHERE id_proveedor=?")
//@FilterDef(name = "deletedProveedoresFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProveedoresFilter", condition = "deleted = :isDeleted")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private long idProveedor;
	private String nombreProveedor;
	private String direccionProveedor;
	private String telefonoProveedor;
	private String emailProveedor;
	private boolean deleted = Boolean.FALSE;
	
	public Proveedor() {
		super();
	}

	public Proveedor(long idProveedor, String nombreProveedor, String direccionProveedor, String telefonoProveedor,
			String emailProveedor, boolean deleted) {
		super();
		this.idProveedor = idProveedor;
		this.nombreProveedor = nombreProveedor;
		this.direccionProveedor = direccionProveedor;
		this.telefonoProveedor = telefonoProveedor;
		this.emailProveedor = emailProveedor;
		this.deleted = deleted;
	}

	public long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public String getEmailProveedor() {
		return emailProveedor;
	}

	public void setEmailProveedor(String emailProveedor) {
		this.emailProveedor = emailProveedor;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
