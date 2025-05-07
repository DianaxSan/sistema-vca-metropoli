package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.CategoriaEntity;
import com.web.repository.CategoriaRepository;
import com.web.service.CategoriaService;

@Service
public class CategoriaImplement implements CategoriaService {
	
	@Autowired
    private CategoriaRepository repo;

	@Override
	public List<CategoriaEntity> listarCategorias() {
		return repo.findAll();
	}

	@Override
	public CategoriaEntity registrarCategoria(CategoriaEntity objcategoria) {
		return repo.save(objcategoria);
	}

	@Override
	public CategoriaEntity editarCategoria(CategoriaEntity objcategoria) {		
		return repo.save(objcategoria);
	}

	@Override
	public CategoriaEntity buscarCategoria(int idcategoria) {
		return repo.findById(idcategoria).orElse(null);
	}

	@Override
	public void eliminarCategoria(int idcategoria) {
		this.repo.deleteById(idcategoria);
		
	}

}
