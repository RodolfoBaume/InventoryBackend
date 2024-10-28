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
@Table(name="productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private long idProducto;
	private String sku;
	private String nombreProducto;
	private String descripcionProducto;
	private double precio;
	private int cantidad;
	private Date fecha_creacion;
	private Date fecha_actualizacion;
	private int minimo;
	private int maximo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidadMedidaId")
	private UnidadMedida unidadMedida;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marcaId")
	private Marca marca;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaId")
	private Categoria categoria;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "proveedorId")
	private Proveedor proveedor;
	
	
	
	public Producto() {
		super();
	}

	public Producto(long idProducto, String sku, String nombreProducto, String descripcionProducto, double precio,
			int cantidad, Date fecha_creacion, Date fecha_actualizacion, int minimo, int maximo, Categoria categoria,
			Proveedor proveedor) {
		super();
		this.idProducto = idProducto;
		this.sku = sku;
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.fecha_creacion = fecha_creacion;
		this.fecha_actualizacion = fecha_actualizacion;
		this.minimo = minimo;
		this.maximo = maximo;
		this.categoria = categoria;
		this.proveedor = proveedor;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
