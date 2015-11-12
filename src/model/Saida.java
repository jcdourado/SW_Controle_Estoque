package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Saida {
	private int idSaida;
	private Date data;
	private String descricao;
	private List<Item> itens = new ArrayList<Item>();
	private List<ProdutoSolicitacaoSaida> solicitacoes = new ArrayList<ProdutoSolicitacaoSaida>();
	
	public int getIdSaida() {
		return idSaida;
	}
	public void setIdSaida(int idSaida) {
		this.idSaida = idSaida;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public void adiciona(Item i){
		itens.add(i);
	}
	public void remove(Item i){
		itens.remove(i);
	}
	public void removePorIndice(int i){
		itens.remove(i);
	}
	public List<ProdutoSolicitacaoSaida> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<ProdutoSolicitacaoSaida> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public void adicionaSolicitacao(ProdutoSolicitacaoSaida s){
		solicitacoes.add(s);
	}
	public void removeSolicitacao(ProdutoSolicitacaoSaida s){
		solicitacoes.remove(s);
	}
	public void removeSolicitacaoPorIndice(int i){
		solicitacoes.remove(i);
	}
	public float qtdTotalItens(){
		return itens.size();
	}
	public float qtdTotalItemProduto(Produto p){
		float qtd = 0;
		for(Item i : itens){
			if (i.getIdProduto() == p.getId()){
				qtd++;
			}
		}
		return qtd;
	}
}