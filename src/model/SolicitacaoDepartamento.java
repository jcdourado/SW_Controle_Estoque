package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitacaoDepartamento {
	private int id;
	private Date data;
	private int idDepartamento;
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
}