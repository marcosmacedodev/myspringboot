package com.myspringboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.myspringboot.model.enums.Perfil;
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

@Service
public class DBService {
	
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
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	public void instantiateDevDatabase() throws ParseException {
		instantiateTestDatabase();
	}
	
	public void instantiateTestDatabase() throws ParseException
	{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia");
		Cidade c2 = new Cidade(null, "São Paulo");
		Cidade c3 = new Cidade(null, "Campinas");
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "marcos.macedo89@outlook.com.br", "36378912377", TipoCliente.PESSOA_FISICA, bcpe.encode("123"));
		cli1.getTelefones().add("27363323");
		cli1.getTelefones().add("93838393");
		
		Cliente cli2 = new Cliente(null, "Jurema Gonçalves", "ricardo.paiva.macedo@hotmail.com", "36378912379", TipoCliente.PESSOA_FISICA, bcpe.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().add("27363323");
		cli2.getTelefones().add("93838393");
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834");
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012");
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "28177012");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"));
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 00:00"), null);
		
		ItemPedido ip1 = new ItemPedido(0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(100.00, 1, 800.00);
		
		//-------------------Associações-----------------------------//
		//-------------------Categorias------------------------------//
		cat1.setProdutos(Arrays.asList(p1, p2, p3, p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		cat2.setProdutos(Arrays.asList(p2, p4));
		cat3.setProdutos(Arrays.asList(p5, p6));
		cat4.setProdutos(Arrays.asList(p1, p2, p3, p7));
		cat5.setProdutos(Arrays.asList(p8));
		cat6.setProdutos(Arrays.asList(p9, p10));
		cat7.setProdutos(Arrays.asList(p11));
		//-------------------Categorias------------------------------//
		
		//-------------------Produtos------------------------------//
		p1.setCategorias(Arrays.asList(cat1, cat4));
		p2.setCategorias(Arrays.asList(cat1, cat2, cat4));
		p3.setCategorias(Arrays.asList(cat1, cat4));
		p4.setCategorias(Arrays.asList(cat2));
		p5.setCategorias(Arrays.asList(cat3));
		p6.setCategorias(Arrays.asList(cat3));
		p7.setCategorias(Arrays.asList(cat4));
		p8.setCategorias(Arrays.asList(cat5));
		p9.setCategorias(Arrays.asList(cat6));
		p10.setCategorias(Arrays.asList(cat6));
		p11.setCategorias(Arrays.asList(cat7));
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		
		p1.getItensPedido().addAll(Arrays.asList(ip1));
		p2.getItensPedido().addAll(Arrays.asList(ip3));
		p3.getItensPedido().addAll(Arrays.asList(ip2));
		
		//-------------------Produtos------------------------------//
		
		//-------------------Estado------------------------------//
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c3));
		//-------------------Estado------------------------------//
		
		//-------------------Cidade------------------------------//
		c1.setEstado(est1);
		c2.setEstado(est2);
		c3.setEstado(est2);
		//-------------------Cidade------------------------------//
		
		//-------------------Cliente------------------------------//
		cli1.setEnderecos(Arrays.asList(e1, e2));
		cli1.setPedidos(Arrays.asList(ped1, ped2));
		
		cli2.setEnderecos(Arrays.asList(e3));
		//-------------------Cliente------------------------------//
		
		//-------------------Endereço------------------------------//
		e1.setCliente(cli1);
		e2.setCliente(cli1);
		e3.setCliente(cli2);
		
		e1.setCidade(c1);
		e2.setCidade(c2);
		e3.setCidade(c3);
		
		//-------------------Endereço------------------------------//
		
		//-------------------Pedido------------------------------//
		ped1.setCliente(cli1);
		ped2.setCliente(cli1);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		ped1.setEndereco(e1);
		ped2.setEndereco(e2);
		
		pagto1.setPedido(ped1);
		pagto2.setPedido(ped2);
		//-------------------Pedido------------------------------//
		
		//-------------------Item Pedido------------------------------//
		ip1.setPedido(ped1);
		ip2.setPedido(ped1);
		ip3.setPedido(ped2);
		
		ip1.setProduto(p1);
		ip2.setProduto(p3);
		ip3.setProduto(p2);
		//-------------------Item Pedido------------------------------//
		
		//-------------------------Injeção de dados--------------------------//
		cr.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		pr.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		er.saveAll(Arrays.asList(est1, est2));
		cir.saveAll(Arrays.asList(c1, c2, c3));
		clir.saveAll(Arrays.asList(cli1, cli2));
		endr.saveAll(Arrays.asList(e1, e2, e3));
		pedr.saveAll(Arrays.asList(ped1, ped2));
		pagr.saveAll(Arrays.asList(pagto1, pagto2));
		ir.saveAll(Arrays.asList(ip1, ip2, ip3));
		//-------------------------Injeção de dados--------------------------//
	}
}
