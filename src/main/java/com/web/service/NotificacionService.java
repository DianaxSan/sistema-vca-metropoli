package com.web.service;

import java.util.List;

import com.web.model.NotificacionEntity;

public interface NotificacionService {
	
	public List<NotificacionEntity> listarNotificaciones();   
	public List<NotificacionEntity> obtenerNotificacionesNoLeidas();
	public NotificacionEntity buscarNotificacion(int idnotificacion);
	public void marcarComoLeida(int idnotificacion);
	public void eliminarNotificacion(int idnotificacion);
	public int contarNotificacionesNoLeidas();

}
