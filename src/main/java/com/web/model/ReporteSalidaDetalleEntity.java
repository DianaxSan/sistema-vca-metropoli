package com.web.model;

public class ReporteSalidaDetalleEntity {
	
	private int idsalida;
    private int idproducto;   
    private String codigo;    
    private String producto;
    private int cantidad;
    private String observacion;
    
    
	public int getIdsalida() {
		return idsalida;
	}
	public void setIdsalida(int idsalida) {
		this.idsalida = idsalida;
	}
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
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
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

    
}
