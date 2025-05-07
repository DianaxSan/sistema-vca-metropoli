package com.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vistainventario")
public class VistaInventarioEntity {
	
	@Id
    @Column(name = "idproducto")
    private int idproducto;
	
	@Column
    private String codigo;

    @Column
    private String nombre;

    @Column
    private double costo;

    @Column
    private double precio;

    @Column
    private int stock;

    @Column(name = "totalcosto")
    private double totalcosto;

    @Column(name = "totalventa")
    private double totalventa;

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getTotalcosto() {
		return totalcosto;
	}

	public void setTotalcosto(double totalcosto) {
		this.totalcosto = totalcosto;
	}

	public double getTotalventa() {
		return totalventa;
	}

	public void setTotalventa(double totalventa) {
		this.totalventa = totalventa;
	}

}
