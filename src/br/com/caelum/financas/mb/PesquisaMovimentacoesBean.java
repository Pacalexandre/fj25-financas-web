package br.com.caelum.financas.mb;

import java.util.List;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

import javax.enterprise.context.RequestScoped;
import javax.inject.*;

@Named
@RequestScoped
public class PesquisaMovimentacoesBean {
	
	@Inject
	MovimentacaoDao dao;

	private Conta conta = new Conta();
	private Integer mes;
	private TipoMovimentacao tipoMovimentacao;
	private List<Movimentacao> movimentacoes;

	
	public void pesquisa() {
		movimentacoes = dao.pesquisa(getConta(), getTipoMovimentacao(), getMes());
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Conta getConta() {
		if(conta.getId()!=null && conta.getId()==0) {
			conta.setId(null);
		}
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getMes() {	
		if(mes!=null && mes==0) {
			mes=null;
		}
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

}
