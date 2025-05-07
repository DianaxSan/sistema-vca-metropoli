package com.web.model;

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
@Table(name = "detallesalidas")
public class DetalleSalidaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetsalida;

	@Column
    private int cantidad;

	@Column
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsalida", nullable = false)
    private SalidaEntity salida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto", nullable = false)
    private ProductoEntity producto;

	public int getIddetsalida() {
		return iddetsalida;
	}

	public void setIddetsalida(int iddetsalida) {
		this.iddetsalida = iddetsalida;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public SalidaEntity getSalida() {
		return salida;
	}

	public void setSalida(SalidaEntity salida) {
		this.salida = salida;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
    
    

}
