package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notificaciones")
public class NotificacionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idnotificacion;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private ProductoEntity producto;

    @Column
    private java.sql.Date fecha;

    @Column
    private String mensaje;

    @Column
    private boolean leida = false;

	public int getIdnotificacion() {
		return idnotificacion;
	}

	public void setIdnotificacion(int idnotificacion) {
		this.idnotificacion = idnotificacion;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public java.sql.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}

}
