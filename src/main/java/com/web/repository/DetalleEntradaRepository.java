package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.DetalleEntradaEntity;

@Repository
public interface DetalleEntradaRepository extends JpaRepository<DetalleEntradaEntity, Integer>{
	
	List<DetalleEntradaEntity> findByEntradaIdentrada(int identrada);

}
