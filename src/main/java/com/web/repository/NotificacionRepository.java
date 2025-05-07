package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.NotificacionEntity;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Integer> {
	List<NotificacionEntity> findByLeidaFalse();
	int countByLeidaFalse();

}
