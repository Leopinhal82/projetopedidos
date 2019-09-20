package com.leo.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.springboot.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Produto findById(long id);
	
}
