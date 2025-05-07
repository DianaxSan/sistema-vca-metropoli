package com.web.service;

import java.util.List;

import com.web.model.EntradaEntity;

public interface EntradaService {
	
	public List<EntradaEntity> listarEntradas();   
	public EntradaEntity registrarEntrada(EntradaEntity objentrada); 	
	public EntradaEntity editarEntrada(EntradaEntity objentrada);    
	public EntradaEntity buscarEntrada(int identrada);    
	public void eliminarEntrada(int identrada);
	
	//public void registrarEntradaProcedure(EntradaEntity objentrada, int pidproducto, int pcantidad, String pobservacion);
	
	public void registrarEntradaProcedure(EntradaEntity entrada, String productosJson);
	public void eliminarEntradaYdetalles(int identrada);

}
