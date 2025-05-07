package com.web.model;

import java.util.List;

public class ReporteSalidaEntity {
	
	private int idsalida;
	private java.sql.Date fecha;
    private String motivo;
    private List<ReporteSalidaDetalleEntity> detalles;
    
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
	public List<ReporteSalidaDetalleEntity> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<ReporteSalidaDetalleEntity> detalles) {
		this.detalles = detalles;
	}
    
    

}
