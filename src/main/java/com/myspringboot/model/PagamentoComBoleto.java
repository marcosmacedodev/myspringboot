package com.myspringboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myspringboot.model.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE) 
	private Date dataVencimento;
	@Temporal(TemporalType.DATE) 
	private Date dataPagamento;
	public PagamentoComBoleto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Date dataVencimento, Date dataPagamento) {
		super(id, estado);
		// TODO Auto-generated constructor stub
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	
}
