package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

}
