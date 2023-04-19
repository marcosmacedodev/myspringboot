package com.myspringboot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.model.Cliente;
import com.myspringboot.model.ItemPedido;
import com.myspringboot.model.PagamentoComBoleto;
import com.myspringboot.model.Pedido;
import com.myspringboot.model.dto.PedidoDTO;
import com.myspringboot.model.enums.EstadoPagamento;
import com.myspringboot.repositories.PedidoRepository;
import com.myspringboot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pr;
	@Autowired
	private BoletoService bs;
	@Autowired
	private PagamentoService pas;
	@Autowired
	private ProdutoService ps;
	@Autowired
	private ItemPedidoService ips;
	@Autowired
	private ClienteService cls;
	@Autowired
	private EnderecoService es;
	@Autowired
	private EmailService emailService;
	
	public List<Pedido> findAll(){
		return pr.findAll();
	}
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(cls.find(pedido.getCliente().getId()));
		pedido.setEndereco(es.find(pedido.getEnderecoDeEntrega().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagtoBoleto = (PagamentoComBoleto) pedido.getPagamento();
			bs.preencherPagamentoComBoleto(pagtoBoleto, pedido.getInstante());
		}
		pedido = pr.save(pedido);
		pas.insert(pedido.getPagamento());
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(ps.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		ips.insertAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
	}
	
	public Pedido remove(Integer id) {
		Pedido pedido = find(id);
		pr.delete(pedido);
		return pedido;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return pr.findAll(pageRequest);
	}
	
	public Pedido toPedido(PedidoDTO pedidoDTO) {
		return new Pedido(pedidoDTO.getId(), pedidoDTO.getInstante());
	}

}
