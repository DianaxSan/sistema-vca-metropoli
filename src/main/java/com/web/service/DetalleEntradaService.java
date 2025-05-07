package com.web.service;

import java.util.List;
import com.web.model.DetalleEntradaEntity;

public interface DetalleEntradaService {
	
	public List<DetalleEntradaEntity> listarDetalleEntradas();   
	public DetalleEntradaEntity registrarDetalleEntrada(DetalleEntradaEntity objdetalleentrada);    
	public DetalleEntradaEntity editarDetalleEntrada(DetalleEntradaEntity objdetalleentrada);    
	public DetalleEntradaEntity buscarDetalleEntrada(int iddetentrada);    
	public void eliminarDetalleEntrada(int iddetentrada);
	
	public List<DetalleEntradaEntity> listarDetalleEntradasPorEntradaId(int identrada);

}
