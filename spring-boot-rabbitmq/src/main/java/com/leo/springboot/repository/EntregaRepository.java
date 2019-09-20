package com.leo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.springboot.models.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long>{
	Entrega findById(long id);
}
