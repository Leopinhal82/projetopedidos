package com.leo.springboot.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.springboot.models.Cliente;
import com.leo.springboot.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteRepository cr;
	
	@GetMapping
	public Iterable<Cliente> listaClientes() {
		Iterable<Cliente> listaCliente = cr.findAll();
		return listaCliente;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaCliente(@PathVariable Long id){
		
		Cliente cliente = cr.findById(id).get();
		
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping()
	public Cliente cadastraCliente(@RequestBody @Valid Cliente cliente) {
		return cr.save(cliente);
	}
	
	/*@PutMapping("{/id}")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente existente = cr.findById(id);
		
		if(existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cliente, existente, "id");
		
		existente = cr.save(existente);
		
		return ResponseEntity.ok(existente);
	}*/
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluiCliente(@PathVariable Long id) {
		Cliente cliente = cr.findById(id).get();
		
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		cr.delete(cliente);
		return ResponseEntity.noContent().build();
	}
}
