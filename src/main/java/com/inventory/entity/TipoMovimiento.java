package com.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipos_movimiento")
public class TipoMovimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_movimiento")
	private long idTipoMovimiento;
	private String tipoMovimiento;

	public TipoMovimiento() {
		super();
	}

	public TipoMovimiento(long idTipoMovimiento, String tipoMovimiento) {
		super();
		this.idTipoMovimiento = idTipoMovimiento;
		this.tipoMovimiento = tipoMovimiento;
	}

	public long getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public void setIdTipoMovimiento(long idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}
