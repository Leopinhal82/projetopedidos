package com.leo.springboot.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.leo.springboot.models.Entrega;
import com.leo.springboot.models.Pedido;
import com.leo.springboot.repository.EntregaRepository;

@RestController
@RequestMapping("/entrega")
public class EntregaResource {

	@Autowired
	private EntregaRepository er;
	
	@GetMapping
	public Iterable<Entrega> listaEntrega() {
		Iterable<Entrega> listaEntrega = er.findAll();
		return listaEntrega;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entrega> buscaEntrega(@PathVariable Long id){
		
		Entrega entrega = er.findById(id).get();
		
		if(entrega == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entrega);
	}
	
	public ResponseEntity<Entrega> cadastraEntrega(String entregaJson) {
		
		Gson gson = new Gson();
		Entrega entregaCad = new Entrega();
		
		Pedido pedidoObj = gson.fromJson(entregaJson, Pedido.class);
			
		entregaCad.setPedido(pedidoObj);
		
		entregaCad.setEnderecoEntrega(pedidoObj.getCliente().getEnderecoEntrega()); 
		
		entregaCad = er.save(entregaCad);
			
		return ResponseEntity.ok(entregaCad);
	}	
	
}