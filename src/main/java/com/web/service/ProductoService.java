package com.web.service;

import java.util.List;

import com.web.model.ProductoEntity;
public interface ProductoService {
	
	public List<ProductoEntity> listarProductos();
	public ProductoEntity registrarProducto(ProductoEntity objproducto);
	public ProductoEntity editarProducto(ProductoEntity objproducto);
	public ProductoEntity buscarProducto(int idproducto);
	public void eliminarProducto(int idproducto);
	
	
	//Método para encontrar usuarios vinculados a un rol
	public boolean hayProductosConEstaCat(int idcategoria);

}
