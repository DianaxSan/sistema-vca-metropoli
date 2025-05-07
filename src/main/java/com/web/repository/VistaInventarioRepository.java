package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.VistaInventarioEntity;

@Repository
public interface VistaInventarioRepository extends JpaRepository<VistaInventarioEntity, Integer> {

}
