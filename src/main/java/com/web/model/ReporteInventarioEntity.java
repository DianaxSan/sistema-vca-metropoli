package com.web.model;

import java.util.List;

public class ReporteInventarioEntity {
	
	private List<ProductoEntity> productos;
	private double totalCosto;
    private double totalVenta;
    private double costoTotalInventario;
    private double valorTotalInventario;

    // Getters y Setters
    public List<ProductoEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoEntity> productos) {
        this.productos = productos;
    }

    public double getCostoTotalInventario() {
        return costoTotalInventario;
    }

    public void setCostoTotalInventario(double costoTotalInventario) {
        this.costoTotalInventario = costoTotalInventario;
    }

    public double getValorTotalInventario() {
        return valorTotalInventario;
    }

    public void setValorTotalInventario(double valorTotalInventario) {
        this.valorTotalInventario = valorTotalInventario;
    }

	public double getTotalCosto() {
		return totalCosto;
	}

	public void setTotalCosto(double totalCosto) {
		this.totalCosto = totalCosto;
	}

	public double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(double totalVenta) {
		this.totalVenta = totalVenta;
	}
    
    

}
