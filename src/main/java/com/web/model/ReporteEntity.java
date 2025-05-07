package com.web.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reportes")
public class ReporteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreporte;

    @ManyToOne
    @JoinColumn(name = "idtiporeporte", nullable = false)
    private TipoReporteEntity tipoReporte;

    @Column(name = "fechageneracion")
    private Timestamp fechaGeneracion;

    @Column(name = "parametros", columnDefinition = "json")
    private String parametros;
    
    @Column(name = "contenido", columnDefinition = "json")
    private String contenido;

	public int getIdreporte() {
		return idreporte;
	}

	public void setIdreporte(int idreporte) {
		this.idreporte = idreporte;
	}

	public TipoReporteEntity getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(TipoReporteEntity tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public Timestamp getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Timestamp fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	
    
}
