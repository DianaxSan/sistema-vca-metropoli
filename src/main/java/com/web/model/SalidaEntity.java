package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salidas")
public class SalidaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsalida;

	@Column
	private java.sql.Date fecha;

	@Column
    private String motivo;

	public int getIdsalida() {
		return idsalida;
	}

	public void setIdsalida(int idsalida) {
		this.idsalida = idsalida;
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
