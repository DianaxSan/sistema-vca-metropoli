package com.web.service;

import java.util.List;



import com.web.model.UsuarioEntity;

public interface UsuarioService {
	
	public List<UsuarioEntity> listarUsuarios();
	public UsuarioEntity registrarUsuario(UsuarioEntity objusuario);
	public UsuarioEntity editarUsuario(UsuarioEntity objusuario);
	public UsuarioEntity buscarUsuario(int idusuario);
	public void eliminarUsuario(int idusuario);
	
	//Método para buscar nombreUsuario
	UsuarioEntity buscarPorNombreUsuario(String usuario);
	
	//Método para encontrar usuarios vinculados a un rol
	public boolean hayUsuariosConEsteRol(int idrol);
	
	

}
