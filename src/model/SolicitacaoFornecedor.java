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
}