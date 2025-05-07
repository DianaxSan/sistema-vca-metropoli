package com.web.serviceimplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.web.model.EntradaEntity;
import com.web.repository.EntradaRepository;
import com.web.service.EntradaService;
import jakarta.transaction.Transactional;

@Service
public class EntradaImplement implements EntradaService{
	
	@Autowired
    private EntradaRepository repo;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<EntradaEntity> listarEntradas() {
		return repo.findAll();
	}

	@Transactional
	@Override
	public EntradaEntity registrarEntrada(EntradaEntity objentrada) {
		return repo.save(objentrada);
		
	}
	
	@Transactional
	@Override
	public EntradaEntity editarEntrada(EntradaEntity objentrada) {
		return repo.save(objentrada);
	}

	@Override
	public EntradaEntity buscarEntrada(int identrada) {
		return repo.findById(identrada).orElse(null);
	}

	@Transactional
	@Override
	public void eliminarEntrada(int identrada) {
		this.repo.deleteById(identrada);
		
	}

	@Override
	public void registrarEntradaProcedure(EntradaEntity entrada, String productosJson) {
		System.out.println("Fecha: " + entrada.getFecha());
	    System.out.println("Motivo: " + entrada.getMotivo());
	    System.out.println("Productos JSON: " + productosJson);
		jdbcTemplate.update("CALL spregistrarentrada(?,?,?)",
                entrada.getFecha(), entrada.getMotivo(), productosJson);
		
		
	}

	@Override
	public void eliminarEntradaYdetalles(int identrada) {
		// Primero eliminar los detalles de la entrada
	    String EliminarDetallesBD = "DELETE FROM detalleentradas WHERE identrada = ?";
	    jdbcTemplate.update(EliminarDetallesBD, identrada);

	    // Luego eliminar la entrada
	    repo.deleteById(identrada);
		
	}

}
