package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoFornecedor {
	private int id;
	private int idFornecedor;
	private Date data;
	private List<SolicitacaoProdutoFornecedor> solicitacoes = new ArrayList<SolicitacaoProdutoFornecedor>();
	private List<ProdutoSolicitacaoEntrada> entregues = new ArrayList<ProdutoSolicitacaoEntrada>();
	private float qtdRestante;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<SolicitacaoProdutoFornecedor> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<SolicitacaoProdutoFornecedor> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public void adiciona(SolicitacaoProdutoFornecedor s){
		solicitacoes.add(s);
	}
	public void removeSolicitacao(SolicitacaoProdutoFornecedor s){
		solicitacoes.remove(s);
	}
	public void removePorIndice(int i){
		solicitacoes.remove(i);
	}
	public List<ProdutoSolicitacaoEntrada> getEntregues() {
		return entregues;
	}
	public void setEntregues(List<ProdutoSolicitacaoEntrada> entregues) {
		this.entregues = entregues;
	}
	public void adicionaEntregue(ProdutoSolicitacaoEntrada s){
		entregues.add(s);
	}
	public void removeEntregue(ProdutoSolicitacaoEntrada s){
		entregues.remove(s);
	}
	public void removeEntreguePorIndice(int i){
		entregues.remove(i);
	}
	public float qtdRestante(Produto p){
		int qtdNecessaria = 0;
		int qtdInterna = 0;
		for(SolicitacaoProdutoFornecedor sol : solicitacoes){
			if(sol.getIdProduto() == p.getId()){
				qtdNecessaria += sol.getQuantidade();
			}
		}
		for(ProdutoSolicitacaoEntrada sol : entregues){
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
		for(SolicitacaoProdutoFornecedor sol : solicitacoes){
			qtdNecessaria += sol.getQuantidade();
		}
		for(ProdutoSolicitacaoEntrada entre : entregues){
			qtdInterna += entre.getQuantidade();
		}
		if(qtdNecessaria < qtdInterna){
			return 0;
		}
		return (qtdNecessaria - qtdInterna);
	}
}