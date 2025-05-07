package com.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.DetalleSalidaEntity;


@Repository
public interface DetalleSalidaRepository extends JpaRepository<DetalleSalidaEntity, Integer>{
	
	List<DetalleSalidaEntity> findBySalidaIdsalida(int idsalida);

}
