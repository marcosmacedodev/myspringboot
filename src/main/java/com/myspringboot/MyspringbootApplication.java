package com.myspringboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myspringboot.model.Categoria;
import com.myspringboot.model.Cidade;
import com.myspringboot.model.Cliente;
import com.myspringboot.model.Endereco;
import com.myspringboot.model.Estado;
import com.myspringboot.model.ItemPedido;
import com.myspringboot.model.Pagamento;
import com.myspringboot.model.PagamentoComBoleto;
import com.myspringboot.model.PagamentoComCartao;
import com.myspringboot.model.Pedido;
import com.myspringboot.model.Produto;
import com.myspringboot.model.enums.EstadoPagamento;
import com.myspringboot.model.enums.TipoCliente;
import com.myspringboot.repositories.CategoriaRepository;
import com.myspringboot.repositories.CidadeRepository;
import com.myspringboot.repositories.ClienteRepository;
import com.myspringboot.repositories.EnderecoRepository;
import com.myspringboot.repositories.EstadoRepository;
import com.myspringboot.repositories.ItemPedidoRepository;
import com.myspringboot.repositories.PagamentoRepository;
import com.myspringboot.repositories.PedidoRepository;
import com.myspringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class MyspringbootApplication implements CommandLineRunner{

	@Autowired
	private ProdutoRepository pr;
	@Autowired
	private CategoriaRepository cr;
	@Autowired
	private EstadoRepository er;
	@Autowired
	private CidadeRepository cir;
	@Autowired
	private ClienteRepository clir;
	@Autowired
	private EnderecoRepository endr;
	@Autowired
	private PagamentoRepository pagr;
	@Autowired
	private ItemPedidoRepository ir;
	@Autowired
	private PedidoRepository pedr;
	
	public static void main(String[] args) {
		SpringApplication.run(MyspringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia");
		Cidade c2 = new Cidade(null, "São Paulo");
		Cidade c3 = new Cidade(null, "Campinas");
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().add("27363323");
		cli1.getTelefones().add("93838393");
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834");
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"));
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 00:00"), null);
		
		ItemPedido ip1 = new ItemPedido(0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(100.00, 1, 800.00);
		
		//------------------------------------------------//
		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		p1.getItensPedido().addAll(Arrays.asList(ip1));
		p2.getItensPedido().addAll(Arrays.asList(ip3));
		p3.getItensPedido().addAll(Arrays.asList(ip2));
		
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c3));
		
		c1.setEstado(est1);
		c2.setEstado(est2);
		c3.setEstado(est2);
		
		cli1.setEnderecos(Arrays.asList(e1, e2));
		cli1.setPedidos(Arrays.asList(ped1, ped2));
		
		e1.setCliente(cli1);
		e2.setCliente(cli1);
		
		e1.setCidade(c1);
		e2.setCidade(c2);
		
		ped1.setCliente(cli1);
		ped2.setCliente(cli1);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		ped1.getItensPedido().addAll(Arrays.asList(ip1, ip2));
		ped2.getItensPedido().addAll(Arrays.asList(ip3));
		
		ped1.setEndereco(e1);
		ped2.setEndereco(e2);
		
		pagto1.setPedido(ped1);
		pagto2.setPedido(ped2);
		
		ip1.setPedido(ped1);
		ip2.setPedido(ped1);
		ip3.setPedido(ped2);
		
		ip1.setProduto(p1);
		ip2.setProduto(p3);
		ip3.setProduto(p2);
		
		//---------------------------------------------------//
		
		cr.saveAll(Arrays.asList(cat1, cat2));
		pr.saveAll(Arrays.asList(p1, p2, p3));
		er.saveAll(Arrays.asList(est1, est2));
		cir.saveAll(Arrays.asList(c1, c2, c3));
		clir.saveAll(Arrays.asList(cli1));
		endr.saveAll(Arrays.asList(e1, e2));
		pedr.saveAll(Arrays.asList(ped1, ped2));
		pagr.saveAll(Arrays.asList(pagto1, pagto2));
		ir.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
