package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.DetalleSalidaEntity;
import com.web.repository.DetalleSalidaRepository;
import com.web.service.DetalleSalidaService;

@Service
public class DetalleSalidaImplement implements DetalleSalidaService{
	
	@Autowired
	private DetalleSalidaRepository repo;

	@Override
	public List<DetalleSalidaEntity> listarDetalleSalidas() {
		return repo.findAll();
	}

	@Override
	public DetalleSalidaEntity registrarDetalleSalida(DetalleSalidaEntity objdetallesalida) {
		return repo.save(objdetallesalida);
	}

	@Override
	public DetalleSalidaEntity editarDetalleSalida(DetalleSalidaEntity objdetallesalida) {
		return repo.save(objdetallesalida);
	}

	@Override
	public DetalleSalidaEntity buscarDetalleSalida(int iddetsalida) {
		return repo.findById(iddetsalida).orElse(null);
	}

	@Override
	public void eliminarDetalleSalida(int iddetsalida) {
		this.repo.deleteById(iddetsalida);
		
	}

	@Override
	public List<DetalleSalidaEntity> listarDetalleSalidasPorSalidaId(int idsalida) {
		return repo.findBySalidaIdsalida(idsalida);
	}

}
