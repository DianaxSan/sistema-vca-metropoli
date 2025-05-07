package com.web.serviceimplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.model.RolEntity;
import com.web.model.UsuarioEntity;
import com.web.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class DetalleUsuarioImplement implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository repo;
    
    //@Autowired
    //private PasswordEncoder passwordEncoder;

	@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = repo.findByUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new User(usuario.getUsuario(), usuario.getClave(), getAuthorities(usuario.getRol()));
    }
    
    private List<GrantedAuthority> getAuthorities(RolEntity rol) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        return authorities;
    }
	

}
