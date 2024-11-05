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

/**
 * Representa un producto en el sistema de inventario.
 * Esta entidad contiene la información principal de un producto, incluyendo su SKU, nombre,
 * descripción, precio, cantidad disponible, y atributos adicionales como mínimo y máximo de inventario.
 * Además, la clase relaciona al producto con otras entidades como unidad de medida, marca, categoría y proveedor.
 * 
 * @autor RBaume
 */
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoProductoId")
    private TipoProducto tipoProducto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedorId")
    private Proveedor proveedor;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Producto() {
        super();
    }

    /**
     * Constructor que permite crear un producto con todos sus atributos.
     *
     * @param idProducto Identificador único del producto
     * @param sku Código único del producto
     * @param nombreProducto Nombre del producto
     * @param descripcionProducto Descripción del producto
     * @param precio Precio del producto
     * @param cantidad Cantidad disponible del producto
     * @param fecha_creacion Fecha de creación del registro
     * @param fecha_actualizacion Fecha de la última actualización del registro
     * @param minimo Mínimo permitido en inventario
     * @param maximo Máximo permitido en inventario
     * @param tipoProductoId Tipo de Producto
     * @param proveedor Proveedor del producto
     */
    

    // Métodos de acceso (getters y setters)

    /**
     * Obtiene el identificador único del producto.
     *
     * @return id del producto
     */
    public long getIdProducto() {
        return idProducto;
    }

    public Producto(long idProducto, String sku, String nombreProducto, String descripcionProducto, double precio,
			int cantidad, Date fecha_creacion, Date fecha_actualizacion, int minimo, int maximo,
			UnidadMedida unidadMedida, Marca marca, TipoProducto tipoProducto, 	Proveedor proveedor) {
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
		this.unidadMedida = unidadMedida;
		this.marca = marca;
		this.tipoProducto = tipoProducto;
		this.proveedor = proveedor;
	}

	/**
     * Asigna el identificador único del producto.
     *
     * @param idProducto id del producto
     */
    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el SKU (código único) del producto.
     *
     * @return SKU del producto
     */
    public String getSku() {
        return sku;
    }

    /**
     * Asigna el SKU (código único) del producto.
     *
     * @param sku SKU del producto
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Asigna el nombre del producto.
     *
     * @param nombreProducto nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return descripción del producto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * Asigna la descripción del producto.
     *
     * @param descripcionProducto descripción del producto
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio del producto.
     *
     * @param precio precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     *
     * @return cantidad disponible del producto
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad disponible del producto.
     *
     * @param cantidad cantidad disponible del producto
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la fecha de creación del producto.
     *
     * @return fecha de creación del producto
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * Asigna la fecha de creación del producto.
     *
     * @param fecha_creacion fecha de creación del producto
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * Obtiene la fecha de actualización del producto.
     *
     * @return fecha de actualización del producto
     */
    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    /**
     * Asigna la fecha de actualización del producto.
     *
     * @param fecha_actualizacion fecha de actualización del producto
     */
    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    /**
     * Obtiene el valor mínimo de inventario permitido para el producto.
     *
     * @return mínimo de inventario
     */
    public int getMinimo() {
        return minimo;
    }

    /**
     * Asigna el valor mínimo de inventario permitido para el producto.
     *
     * @param minimo mínimo de inventario
     */
    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    /**
     * Obtiene el valor máximo de inventario permitido para el producto.
     *
     * @return máximo de inventario
     */
    public int getMaximo() {
        return maximo;
    }

    /**
     * Asigna el valor máximo de inventario permitido para el producto.
     *
     * @param maximo máximo de inventario
     */
    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    /**
     * Obtiene la unidad de medida del producto.
     *
     * @return unidad de medida del producto
     */
    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Asigna la unidad de medida del producto.
     *
     * @param unidadMedida unidad de medida del producto
     */
    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * Obtiene la marca del producto.
     *
     * @return marca del producto
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * Asigna la marca del producto.
     *
     * @param marca marca del producto
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
    /**
     * Obtiene el tipo de producto al que pertenece el producto.
     *
     * @return tipo de producto
     */
    public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

    /**
     * Asigna el tipo de producto al que pertenece el producto.
     *
     * @param tipoProducto tipo de producto
     */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

    /**
     * Obtiene el proveedor del producto.
     *
     * @return proveedor del producto
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Asigna el proveedor del producto.
     *
     * @param proveedor proveedor del producto
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
