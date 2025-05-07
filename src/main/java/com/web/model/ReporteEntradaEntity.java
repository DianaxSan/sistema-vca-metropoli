package com.web.model;

import java.util.List;

public class ReporteEntradaEntity {
	
	private int identrada;
	private java.sql.Date fecha;
    private String motivo;
    private List<ReporteEntradaDetalleEntity> detalles;
    
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
	public List<ReporteEntradaDetalleEntity> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<ReporteEntradaDetalleEntity> detalles) {
		this.detalles = detalles;
	}
	
    
   
}
