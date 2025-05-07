package com.web.service;

import java.util.List;
import com.web.model.CategoriaEntity;

public interface CategoriaService {
	
	public List<CategoriaEntity> listarCategorias();   
	public CategoriaEntity registrarCategoria(CategoriaEntity objcategoria);    
	public CategoriaEntity editarCategoria(CategoriaEntity objcategoria);    
	public CategoriaEntity buscarCategoria(int idcategoria);    
	public void eliminarCategoria(int idcategoria);

}
