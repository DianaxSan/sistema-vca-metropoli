package com.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.model.ReporteEntity;

@Repository
public interface ReporteRepository extends JpaRepository<ReporteEntity, Integer>{
	
	@Query("SELECT r FROM ReporteEntity r WHERE r.tipoReporte = :tipoReporte")
    ReporteEntity findByTipoReporte(@Param("tipoReporte") int tipoReporte);
	

}
