package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoDepartamento {
	private int id;
	private Date data;
	private int idDepartamento;
	private float qtdRestante;
	private List<SolicitacaoProdutoDepartamento> solicitacoes = new ArrayList<SolicitacaoProdutoDepartamento>();
	private List<ProdutoSolicitacaoSaida> recebidos = new ArrayList<ProdutoSolicitacaoSaida>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public List<SolicitacaoProdutoDepartamento> getSoliticacoes() {
		return solicitacoes;
	}
	public void setSoliticacoes(List<SolicitacaoProdutoDepartamento> soliticacoes) {
		this.solicitacoes = soliticacoes;
	}
	public void adiciona(SolicitacaoProdutoDepartamento s){
		solicitacoes.add(s);
	}
	public void removeSolicitacao(SolicitacaoProdutoDepartamento s){
		solicitacoes.remove(s);
	}
	public void removePorIndice(int i){
		solicitacoes.remove(i);
	}
	public List<ProdutoSolicitacaoSaida> getRecebidos() {
		return recebidos;
	}
	public void setRecebidos(List<ProdutoSolicitacaoSaida> recebidos) {
		this.recebidos = recebidos;
	}
	public void adicionaRecebido(ProdutoSolicitacaoSaida s){
		recebidos.add(s);
	}
	public void removeRecebido(ProdutoSolicitacaoSaida s){
		recebidos.remove(s);
	}
	public void removeRecebidoPorIndice(int i){
		recebidos.remove(i);
	}
	public float qtdRestante(Produto p){
		int qtdNecessaria = 0;
		int qtdInterna = 0;
		for(SolicitacaoProdutoDepartamento sol : solicitacoes){
			if(sol.getIdProduto() == p.getId()){
				qtdNecessaria += sol.getQuantidade();
			}
		}
		for(ProdutoSolicitacaoSaida sol : recebidos){
			if(sol.getIdProduto() == p.getId()){
				qtdInterna += sol.getQuantidade();
			}
		}
		if(qtdNecessaria < qtdInterna){
			setQtdRestante(0);
			return 0;
		}
		setQtdRestante(qtdNecessaria - qtdInterna);
		return (qtdNecessaria - qtdInterna);
	}
	public float getQtdRestante(Produto p) {
		qtdRestante(p);
		return qtdRestante;
	}
	public void setQtdRestante(float qtdRestante) {
		this.qtdRestante = qtdRestante;
	}
	public boolean restaAlgo(Produto p){
		if(getQtdRestante(p) > 0){
			return false;
		}
		return true;
	}
	public boolean restaAlgoGeral(){
		if(qtdRestanteGeral() > 0){
			return false;
		}
		return true;
	}
	public float qtdRestanteGeral(){
		int qtdNecessaria = 0;
		int qtdInterna = 0;
		for(SolicitacaoProdutoDepartamento sol : solicitacoes){
			qtdNecessaria += sol.getQuantidade();
		}
		for(ProdutoSolicitacaoSaida sol : recebidos){
			qtdInterna += sol.getQuantidade();
		}
		if(qtdNecessaria < qtdInterna){
			return 0;
		}
		return (qtdNecessaria - qtdInterna);
	}
}