package com.leo.springboot.resources;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.springboot.models.Produto;
import com.leo.springboot.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository pr;
	
	@GetMapping
	public Iterable<Produto> listaProdutos() {
		Iterable<Produto> listaProdutos = pr.findAll();
		return listaProdutos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscaProduto(@PathVariable Long id){
		
		Produto produto = pr.findById(id).get();
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping()
	public Produto cadastraProduto(@RequestBody @Valid Produto produto) {
		return pr.save(produto);
	}
	
	@PutMapping("{/id}")
	public ResponseEntity<Produto> atualizaProduto(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		Produto existente = pr.findById(id).get();
		
		if(existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(produto, existente, "id");
		
		existente = pr.save(existente);
		
		return ResponseEntity.ok(existente);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluiProduto(@PathVariable Long id) {
		Produto produto = pr.findById(id).get();
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		pr.delete(produto);
		return ResponseEntity.noContent().build();
	}
}
