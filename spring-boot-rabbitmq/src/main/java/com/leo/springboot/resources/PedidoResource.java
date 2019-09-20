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

import com.google.gson.Gson;
import com.leo.springboot.models.Cliente;
import com.leo.springboot.models.Pedido;
import com.leo.springboot.models.Produto;
import com.leo.springboot.publisher.Publisher;
import com.leo.springboot.repository.ClienteRepository;
import com.leo.springboot.repository.PedidoRepository;
import com.leo.springboot.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

	@Autowired
	private PedidoRepository pr;
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private ProdutoRepository prr;
	
	@Autowired
	Publisher publisher;
	
	@GetMapping
	public Iterable<Pedido> listaPedidos() {
		Iterable<Pedido> listaPedido = pr.findAll();
		return listaPedido;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscaPedido(@PathVariable Long id){
		
		Pedido peiddo = pr.findById(id).get();
		
		if(peiddo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(peiddo);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Pedido> cadastraPedido(@PathVariable String id, @RequestBody @Valid Pedido pedido) {
		Pedido pedidoCad = new Pedido();
		//Publisher publisher = new Publisher();
		Gson gson = new Gson();
		
		pedidoCad.setDescricao(pedido.getDescricao());
		pedidoCad.setQtdProduto(pedido.getQtdProduto());
        
		String[] arrOfStr = id.split("-", 2); 
				
		try {
			
		
			Cliente cliente = cr.findById(Long.parseLong(arrOfStr[0]));
			Produto produto = prr.findById(Long.parseLong(arrOfStr[1]));
			
			if(cliente.getIdCliente() == 0 || produto.getIdProduto() == 0) {
				return ResponseEntity.notFound().build();
			}
			pedidoCad.setCliente(cliente);
			pedidoCad.setProduto(produto);
			pedidoCad = pr.save(pedidoCad);
			
			// converte objetos Java para JSON e retorna JSON como String
		    String json = gson.toJson(pedidoCad);
			
		    publisher.produceMsg(json);
		    //sender.Sender(json);
		    //sender.run(null);
			
			return ResponseEntity.ok(pedidoCad);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
//	@PutMapping("{/id}")
//	public ResponseEntity<Pedido> atualizaPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
//		Pedido existente = pr.findById(id);
//		
//		if(existente == null) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		BeanUtils.copyProperties(pedido, existente, "id");
//		
//		existente = pr.save(existente);
//		
//		return ResponseEntity.ok(existente);
//	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluiPedido(@PathVariable Long id) {
		Pedido pedido = pr.findById(id).get();
		
		if(pedido == null) {
			return ResponseEntity.notFound().build();
		}
		
		pr.delete(pedido);
		return ResponseEntity.noContent().build();
	}
}