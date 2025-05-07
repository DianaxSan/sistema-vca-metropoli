package com.web.serviceimplement;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.model.ProductoEntity;
import com.web.repository.ProductoRepository;
import com.web.service.ProductoService;

import jakarta.transaction.Transactional;

@Service
public class ProductoImplement implements ProductoService {
	
	@Autowired
    private ProductoRepository repo;
	
	@Override
	public List<ProductoEntity> listarProductos() {
		return repo.findAll();
	}
	
	@Transactional
	@Override
	public ProductoEntity registrarProducto(ProductoEntity objproducto) {
		return repo.save(objproducto);
	}
	
	@Transactional
	@Override
	public ProductoEntity editarProducto(ProductoEntity objproducto) {
		return repo.save(objproducto);
	}
	
	
	@Override
	public ProductoEntity buscarProducto(int idproducto) {
		return repo.findById(idproducto).orElse(null);
	}
	
	@Transactional
	@Override
	public void eliminarProducto(int idproducto) {
		repo.deleteById(idproducto);		
	}


	@Override
	public boolean hayProductosConEstaCat(int idcategoria) {
		return !repo.findByCategoriaId(idcategoria).isEmpty();
	}



}
