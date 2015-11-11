package model;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor {
	private int id;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String tel;
	private float mediaPedida; // conta de media pedida
	private List<SolicitacaoFornecedor> solicitacoes = new ArrayList<SolicitacaoFornecedor>();
	private List<Entrada> entradas = new ArrayList<Entrada>();
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<SolicitacaoFornecedor> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<SolicitacaoFornecedor> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public void adiciona(SolicitacaoFornecedor sf){
		solicitacoes.add(sf);
	}
	public void removeSolicitacao(SolicitacaoFornecedor sf){
		solicitacoes.remove(sf);
	}
	public void removeSolicitacaoPorIndice(int i){
		solicitacoes.remove(i);
	}
	public List<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}
	public void adicionaEntrada(Entrada sf){
		entradas.add(sf);
	}
	public void removeEntrada(Entrada sf){
		entradas.remove(sf);
	}
	public void removeEntradaPorIndice(int i){
		entradas.remove(i);
	}
	public float getMediaPedida() {
		return mediaPedida;
	}
	public void setMediaPedida(float mediaPedida) {
		this.mediaPedida = mediaPedida;
	}	
}