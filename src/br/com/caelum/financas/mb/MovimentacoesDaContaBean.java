package br.com.caelum.financas.mb;

import java.util.List;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;

@Named
@RequestScoped
public class MovimentacoesDaContaBean {
	
	@Inject
	private MovimentacaoDao dao;

	private List<Movimentacao> movimentacoes;
	private Conta conta = new Conta();
	
	public void lista() {
		movimentacoes = dao.listaTodasMovimentacoes(conta);
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
