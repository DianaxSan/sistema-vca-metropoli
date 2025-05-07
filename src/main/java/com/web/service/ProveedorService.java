package com.web.service;

import java.util.List;

import com.web.model.ProveedorEntity;

public interface ProveedorService {
	
	public List<ProveedorEntity> listarProveedores();   
	public ProveedorEntity registrarProveedor(ProveedorEntity objproveedor);    
	public ProveedorEntity editarProveedor(ProveedorEntity objproveedor);    
	public ProveedorEntity buscarProveedor(int idproveedor);    
	public void eliminarProveedor(int idproveedor);

}
