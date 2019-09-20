package com.leo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.springboot.models.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findById(long id);
}
