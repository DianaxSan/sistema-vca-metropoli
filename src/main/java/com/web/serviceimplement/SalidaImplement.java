package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.web.model.SalidaEntity;
import com.web.repository.SalidaRepository;
import com.web.service.SalidaService;

@Service
public class SalidaImplement implements SalidaService{

	@Autowired
    private SalidaRepository repo;
		
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SalidaEntity> listarSalidas() {
		return repo.findAll();
	}

	@Override
	public SalidaEntity registrarSalida(SalidaEntity objsalida) {
		return repo.save(objsalida);
	}

	@Override
	public SalidaEntity editarSalida(SalidaEntity objsalida) {
		return repo.save(objsalida);
	}

	@Override
	public SalidaEntity buscarSalida(int idsalida) {
		return repo.findById(idsalida).orElse(null);
	}

	@Override
	public void eliminarSalida(int idsalida) {
		this.repo.deleteById(idsalida);
		
	}

	@Override
	public void registrarSalidaProcedure(SalidaEntity salida, String productosJson) {
		jdbcTemplate.update("CALL spregistrarsalida(?,?,?)",
                salida.getFecha(), salida.getMotivo(), productosJson);
		
	}

	@Override
	public void eliminarSalidaYdetalles(int idsalida) {
		// Primero eliminar los detalles de la salida
		String EliminarDetallesBD = "DELETE FROM detallesalidas WHERE idsalida = ?";
		jdbcTemplate.update(EliminarDetallesBD, idsalida);

		// Luego eliminar la entrada
		repo.deleteById(idsalida);
		
	}

}
