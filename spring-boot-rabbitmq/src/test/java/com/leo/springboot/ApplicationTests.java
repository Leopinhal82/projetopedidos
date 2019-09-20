package com.leo.springboot;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.springboot.models.Cliente;
import com.leo.springboot.models.Entrega;
import com.leo.springboot.models.Pedido;
import com.leo.springboot.models.Produto;
import com.leo.springboot.repository.ClienteRepository;
import com.leo.springboot.repository.EntregaRepository;
import com.leo.springboot.repository.PedidoRepository;
import com.leo.springboot.repository.ProdutoRepository;
import com.leo.springboot.resources.ClienteResource;
import com.leo.springboot.resources.PedidoResource;
import com.leo.springboot.resources.ProdutoResource;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private ClienteResource cre;
	
	@Autowired
	private ProdutoRepository pr;
	
	@Autowired
	private ProdutoResource pre;
	
	@Autowired
	private PedidoRepository per;
	
	@Autowired
	private PedidoResource pere;
	
	@Autowired
	private EntregaRepository er;
	
	@Test
	public void t01_testaCadastroCliente() {
		
		
		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo Mendes");
		cliente.setEnderecoEntrega("Rua Ernani Lacerda de Athayde");
		
		cre.cadastraCliente(cliente);
		
		Cliente clienteCad = cr.findByIdCliente(1);
		
		Assert.assertNotNull(clienteCad);
		Assert.assertNotNull(clienteCad.getIdCliente());

	}
	
	@Test
	public void t02_testaCadastroProduto() {
		
		Produto produto = new Produto();
		produto.setDescricao("Camisetas");
		produto.setValor(new BigDecimal(15.00));
		
		pre.cadastraProduto(produto);
		
		Produto produtoCad = pr.findByIdProduto(2);
		
		Assert.assertNotNull(produtoCad);
		Assert.assertNotNull(produtoCad.getIdProduto());
	}
	
	@Test
	public void t03_testaCadastroPedido() {
		
		Pedido pedido = new Pedido();
		pedido.setDescricao("Pedido em lote");
		pedido.setQtdProduto(83);

		
		pere.cadastraPedido("1-2", pedido);
		
		Pedido pedidoCad = per.findByIdPedido(3);
		
		Assert.assertNotNull(pedidoCad);
		Assert.assertNotNull(pedidoCad.getIdPedido());
	}
	
	@Test
	public void t04_testaEntrega() {
		
		Entrega entrega = new Entrega();

		entrega = er.findByPedidoIdPedido(Long.parseLong("3"));
		
		Assert.assertNotNull(entrega);
		Assert.assertNotNull(entrega.getIdEntrega());
	}

}
