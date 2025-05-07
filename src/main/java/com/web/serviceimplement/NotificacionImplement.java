package com.web.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.NotificacionEntity;
import com.web.repository.NotificacionRepository;
import com.web.service.NotificacionService;

@Service
public class NotificacionImplement implements NotificacionService {

	@Autowired
    private NotificacionRepository repo;
	
	@Override
	public List<NotificacionEntity> listarNotificaciones() {
		return repo.findAll();
	}
	
	@Override
	public List<NotificacionEntity> obtenerNotificacionesNoLeidas() {
		return repo.findByLeidaFalse();
	}

	@Override
	public void marcarComoLeida(int idnotificacion) {
		NotificacionEntity notificacion = repo.findById(idnotificacion).orElse(null);
        if (notificacion != null) {
            notificacion.setLeida(true);
            repo.save(notificacion);
        }
		
	}

	@Override
	public NotificacionEntity buscarNotificacion(int idnotificacion) {
		return repo.findById(idnotificacion).orElse(null);
	}

	@Override
	public void eliminarNotificacion(int idnotificacion) {
		this.repo.deleteById(idnotificacion);
		
	}

	@Override
	public int contarNotificacionesNoLeidas() {
		return repo.countByLeidaFalse();
	}

}
