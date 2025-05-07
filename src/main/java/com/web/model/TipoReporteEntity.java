package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiporeporte")
public class TipoReporteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtiporeporte;

	@Column
    private String nombre;

	public int getIdtiporeporte() {
		return idtiporeporte;
	}

	public void setIdtiporeporte(int idtiporeporte) {
		this.idtiporeporte = idtiporeporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
