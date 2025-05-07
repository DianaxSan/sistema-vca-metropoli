package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.VistaInventarioEntity;
import com.web.repository.VistaInventarioRepository;
import com.web.service.VistaInventarioService;

@Service
public class VistaInventarioImplement implements VistaInventarioService {

	@Autowired
    private VistaInventarioRepository repo;
	@Override
	public List<VistaInventarioEntity> listarinventario() {
		return repo.findAll();
	}

}
