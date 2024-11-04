package com.inventory.entity;

import java.util.Date;

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
@Table(name="movimientos")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private long id_movimiento;
	private Date fechaMovimiento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productoId")
	private Producto producto;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoMovimientoId")
	private TipoMovimiento tipoMovimiento;
	private int cantidad;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionId")
	private Ubicacion ubicacion;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId")
	private Usuario usuario;

	public Movimiento() {
		super();
	}

	public Movimiento(long id_movimiento, Date fechaMovimiento, Producto producto, TipoMovimiento tipoMovimiento,
			int cantidad, Ubicacion ubicacion, Usuario usuario) {
		super();
		this.id_movimiento = id_movimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.producto = producto;
		this.tipoMovimiento = tipoMovimiento;
		this.cantidad = cantidad;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
	}

	public long getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(long id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
