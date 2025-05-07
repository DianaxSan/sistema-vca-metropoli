package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.model.UsuarioEntity;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	UsuarioEntity findByUsuario(String usuario);
	
	//query para consultar usuarios vinculados a rol
	@Query("SELECT u FROM UsuarioEntity u WHERE u.rol.idrol = :idrol")
    List<UsuarioEntity> findByRolId(@Param("idrol") int idrol);

}
