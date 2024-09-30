package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="estatusOrdenes")
public class EstatusOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estatus_orden")
	private long idEstatusOrden;
	private String estatusOrden;
	
	public EstatusOrden() {
		super();
	}

	public EstatusOrden(long idEstatusOrden, String estatusOrden) {
		super();
		this.idEstatusOrden = idEstatusOrden;
		this.estatusOrden = estatusOrden;
	}

	public long getIdEstatusOrden() {
		return idEstatusOrden;
	}

	public void setIdEstatusOrden(long idEstatusOrden) {
		this.idEstatusOrden = idEstatusOrden;
	}

	public String getEstatusOrden() {
		return estatusOrden;
	}

	public void setEstatusOrden(String estatusOrden) {
		this.estatusOrden = estatusOrden;
	}	
}
