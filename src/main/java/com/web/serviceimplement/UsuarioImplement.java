package com.web.serviceimplement;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.web.model.UsuarioEntity;
import com.web.repository.UsuarioRepository;
import com.web.service.UsuarioService;

@Service
public class UsuarioImplement implements UsuarioService {
	
	@Autowired
    private UsuarioRepository repo;
	
	@Autowired
    private PasswordEncoder passwordEncoder; //agregué
    

	@Override
	public List<UsuarioEntity> listarUsuarios() {
		return repo.findAll();
	}

	@Override
	public UsuarioEntity registrarUsuario(UsuarioEntity objusuario) {
		objusuario.setClave(passwordEncoder.encode(objusuario.getClave()));
		return repo.save(objusuario);
	}

	@Override
	public UsuarioEntity editarUsuario(UsuarioEntity objusuario) {
		//return repo.save(objusuario);
		UsuarioEntity usuarioOriginal = repo.findById(objusuario.getIdusuario()).orElse(null);
	    if (usuarioOriginal != null) {
	        usuarioOriginal.setUsuario(objusuario.getUsuario());
	        if (!objusuario.getClave().isEmpty() && !passwordEncoder.matches(objusuario.getClave(), usuarioOriginal.getClave())) {
	            usuarioOriginal.setClave(passwordEncoder.encode(objusuario.getClave()));
	        }
	        usuarioOriginal.setRol(objusuario.getRol());
	    }
	    return repo.save(usuarioOriginal);
		

	}

	@Override
	public UsuarioEntity buscarUsuario(int idusuario) {
		return repo.findById(idusuario).orElse(null);
	}

	@Override
	public void eliminarUsuario(int idusuario) {
		this.repo.deleteById(idusuario);		
	}

	@Override
	public UsuarioEntity buscarPorNombreUsuario(String usuario) {
		return repo.findByUsuario(usuario);
	}
	
	@Override
	public boolean hayUsuariosConEsteRol(int idrol) {
	    return !repo.findByRolId(idrol).isEmpty();
	}


}
