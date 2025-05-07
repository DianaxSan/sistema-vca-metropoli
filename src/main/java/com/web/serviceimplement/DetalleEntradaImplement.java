package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.DetalleEntradaEntity;
import com.web.repository.DetalleEntradaRepository;
import com.web.service.DetalleEntradaService;

@Service
public class DetalleEntradaImplement implements DetalleEntradaService{

	@Autowired
    private DetalleEntradaRepository repo;
	
	@Override
	public List<DetalleEntradaEntity> listarDetalleEntradas() {
		return repo.findAll();
	}

	@Override
	public DetalleEntradaEntity registrarDetalleEntrada(DetalleEntradaEntity objdetalleentrada) {
		return repo.save(objdetalleentrada);
	}

	@Override
	public DetalleEntradaEntity editarDetalleEntrada(DetalleEntradaEntity objdetalleentrada) {
		return repo.save(objdetalleentrada);
	}

	@Override
	public DetalleEntradaEntity buscarDetalleEntrada(int iddetentrada) {
		return repo.findById(iddetentrada).orElse(null);
	}

	@Override
	public void eliminarDetalleEntrada(int iddetentrada) {
		this.repo.deleteById(iddetentrada);		
	}

	@Override
	public List<DetalleEntradaEntity> listarDetalleEntradasPorEntradaId(int identrada) {
		return repo.findByEntradaIdentrada(identrada);
	}

}
