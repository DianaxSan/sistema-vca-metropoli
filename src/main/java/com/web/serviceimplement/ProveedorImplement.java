package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.ProveedorEntity;
import com.web.repository.ProveedorRepository;
import com.web.service.ProveedorService;

@Service
public class ProveedorImplement implements ProveedorService{

	@Autowired
	private ProveedorRepository repo;
	
	@Override
	public List<ProveedorEntity> listarProveedores() {
		return repo.findAll();
	}

	@Override
	public ProveedorEntity registrarProveedor(ProveedorEntity objproveedor) {
		return repo.save(objproveedor);
	}

	@Override
	public ProveedorEntity editarProveedor(ProveedorEntity objproveedor) {
		return repo.save(objproveedor);
	}

	@Override
	public ProveedorEntity buscarProveedor(int idproveedor) {
		return repo.findById(idproveedor).orElse(null);
	}

	@Override
	public void eliminarProveedor(int idproveedor) {
		this.repo.deleteById(idproveedor);
		
	}

}
