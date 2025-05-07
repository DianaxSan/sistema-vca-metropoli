package com.web.service;

import java.util.List;

import com.web.model.RolEntity;

public interface RolService {
	
	public List<RolEntity> listarRoles();
    public RolEntity registrarRol(RolEntity objrol);
    public RolEntity editarRol(RolEntity objrol);
    public RolEntity buscarRol(int idrol);
    public void eliminarRol(int idrol);

}
