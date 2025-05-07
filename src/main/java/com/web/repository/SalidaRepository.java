package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.model.SalidaEntity;

@Repository
public interface SalidaRepository extends JpaRepository<SalidaEntity, Integer>{

}
