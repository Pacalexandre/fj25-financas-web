package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.*;

@ManagedBean
@ViewScoped
public class MovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentacaoDao dao;
	@Inject
	private ContaDao daoConta;
	@Inject
	private CategoriaDao categoriaDao;

	private List<Movimentacao> movimentacoes;
	private List<Categoria> categorias;
	private Movimentacao movimentacao = new Movimentacao();
	private Conta conta = new Conta();
	private Integer contaId;
	private Integer categoriaId;

	public void grava() {
		if (this.movimentacao.getId() == null) {
			this.conta = daoConta.busca(contaId);
			movimentacao.setConta(this.conta);
			dao.adiciona(movimentacao);
		} else {
			dao.altera(movimentacao);
		}
		this.movimentacoes = dao.lista();
		limpaFormularioDoJSF();
	}

	public void remove() {
		dao.remove(movimentacao);
		this.movimentacoes = dao.lista();
		limpaFormularioDoJSF();
	}

	public List<Movimentacao> getMovimentacoes() {
		if (this.movimentacao.getId() == null) {
			this.movimentacoes = dao.listaComCategoria();
		}
		return movimentacoes;
	}

	public Movimentacao getMovimentacao() {
		if (movimentacao.getData() == null) {
			movimentacao.setData(Calendar.getInstance());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}

	public void adicionaCategoria() {
		if (this.categoriaId != null && this.categoriaId > 0) {
			Categoria categoria = categoriaDao.procura(this.categoriaId);
			this.movimentacao.getCategorias().add(categoria);
		}
	}

	public List<Categoria> getCategorias() {
		if (this.categorias == null) {
			this.categorias = this.categoriaDao.lista();
		}
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
