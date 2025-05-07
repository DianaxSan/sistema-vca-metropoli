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
@Table(name = "detalleentradas")
public class DetalleEntradaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddetentrada;

	@Column
    private int cantidad;

	@Column
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identrada", nullable = false)
    private EntradaEntity entrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproducto", nullable = false)
    private ProductoEntity producto;

	public int getIddetentrada() {
		return iddetentrada;
	}

	public void setIddetentrada(int iddetentrada) {
		this.iddetentrada = iddetentrada;
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

	public EntradaEntity getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaEntity entrada) {
		this.entrada = entrada;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
    
}
