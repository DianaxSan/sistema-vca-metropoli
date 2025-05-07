package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.RolEntity;
import com.web.repository.RolRepository;
import com.web.service.RolService;

@Service
public class RolImplement implements RolService{
	
	@Autowired
    private RolRepository repo;

	@Override
	public List<RolEntity> listarRoles() {
		return repo.findAll();
	}

	@Override
	public RolEntity registrarRol(RolEntity objrol) {
		return repo.save(objrol);
	}

	@Override
	public RolEntity editarRol(RolEntity objrol) {
		return repo.save(objrol);
	}

	@Override
	public RolEntity buscarRol(int idrol) {
		return repo.findById(idrol).orElse(null);
	}

	@Override
	public void eliminarRol(int idrol) {
		//this.repo.deleteById(idrol);
		RolEntity rol = repo.findById(idrol).orElseThrow();
	    repo.delete(rol);
		
		
	}

}
