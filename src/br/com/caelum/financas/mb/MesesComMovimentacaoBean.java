package br.com.caelum.financas.mb;

import java.util.*;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.*;

@Named
@RequestScoped
public class MesesComMovimentacaoBean {

	@Inject
	private MovimentacaoDao dao;

	private Conta conta = new Conta();
	private List<ValorPorMesEAno> valoresPorMesEAno;

	private TipoMovimentacao tipoMovimentacao;

	public void lista() {
		valoresPorMesEAno = dao.listaMesesComMovimentacoes(conta, tipoMovimentacao);
		// System.out.println("Listando as contas pelos valores movimentados no mes");

	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Conta getConta() {
		return conta;
	}

	public List<ValorPorMesEAno> getValoresPorMesEAno() {
		return valoresPorMesEAno;
	}

	public void setValoresPorMesEAno(List<ValorPorMesEAno> valoresPorMesEAno) {
		this.valoresPorMesEAno = valoresPorMesEAno;
	}

}
