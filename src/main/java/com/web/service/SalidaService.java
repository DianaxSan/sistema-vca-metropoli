package com.web.service;

import java.util.List;

import com.web.model.SalidaEntity;

public interface SalidaService {
	
	public List<SalidaEntity> listarSalidas();   
	public SalidaEntity registrarSalida(SalidaEntity objsalida); 	
	public SalidaEntity editarSalida(SalidaEntity objsalida);    
	public SalidaEntity buscarSalida(int idsalida);    
	public void eliminarSalida(int idsalida);
		
	public void registrarSalidaProcedure(SalidaEntity salida, String productosJson);
	public void eliminarSalidaYdetalles(int idsalida);

}
