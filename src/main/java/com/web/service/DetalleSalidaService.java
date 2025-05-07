package com.web.service;

import java.util.List;

import com.web.model.DetalleSalidaEntity;

public interface DetalleSalidaService {
	
	public List<DetalleSalidaEntity> listarDetalleSalidas();   
	public DetalleSalidaEntity registrarDetalleSalida(DetalleSalidaEntity objdetallesalida);    
	public DetalleSalidaEntity editarDetalleSalida(DetalleSalidaEntity objdetallesalida);    
	public DetalleSalidaEntity buscarDetalleSalida(int iddetsalida);    
	public void eliminarDetalleSalida(int iddetsalida);

	public List<DetalleSalidaEntity> listarDetalleSalidasPorSalidaId(int idsalida);

}
