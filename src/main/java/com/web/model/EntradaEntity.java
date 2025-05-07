package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "entradas")
public class EntradaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identrada;

	@Column
	private java.sql.Date fecha;

	@Column
    private String motivo;

	public int getIdentrada() {
		return identrada;
	}

	public void setIdentrada(int identrada) {
		this.identrada = identrada;
	}

	public java.sql.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
