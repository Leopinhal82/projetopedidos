package com.leo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.springboot.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByIdCliente(long id);
}
