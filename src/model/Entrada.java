package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrada {
	private int idEntrada;
	private Date data;
	private String tipoTransf;
	private String NFE;
	private Date dataEmissarNFE;
	private float tempo;
	private int idFornecedor;
	private List<Item> itens = new ArrayList<Item>();
	private List<ProdutoSolicitacaoEntrada> entregues = new ArrayList<ProdutoSolicitacaoEntrada>();
	
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipoTransf() {
		return tipoTransf;
	}
	public void setTipoTransf(String tipoTransf) {
		this.tipoTransf = tipoTransf;
	}
	public String getNFE() {
		return NFE;
	}
	public void setNFE(String nFE) {
		NFE = nFE;
	}
	public Date getDataEmissarNFE() {
		return dataEmissarNFE;
	}
	public void setDataEmissarNFE(Date dataEmissarNFE) {
		this.dataEmissarNFE = dataEmissarNFE;
	}
	public float getTempo() {
		return tempo;
	}
	public void setTempo(float tempo) {
		this.tempo = tempo;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
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
	public List<ProdutoSolicitacaoEntrada> getEntregues() {
		return entregues;
	}
	public void setEntregues(List<ProdutoSolicitacaoEntrada> entregues) {
		this.entregues = entregues;
	}
	public void adicionaEntregue(ProdutoSolicitacaoEntrada sf){
		entregues.add(sf);
	}
	public void removeEntregue(ProdutoSolicitacaoEntrada sf){
		entregues.remove(sf);
	}
	public void removeEntreguePorIndice(int i){
		entregues.remove(i);
	}
}
