package model;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private int id;
	private String nome;
	private String uso;
	private float qtdEmEstoque;
	private float qtdMinima;
	private float qtdMaxima;
	private float qtdSeguranca;
	private float consumo;
	private String consumoPrevisto;
	private float mediaEntrega;
	private float peso;
	private float preco;
	private int tipo;
	private List<SolicitacaoProdutoFornecedor> qtdPedidoFornecedor = new ArrayList<SolicitacaoProdutoFornecedor>();
	private List<SolicitacaoProdutoDepartamento> qtdPedidoDepartamento = new ArrayList<SolicitacaoProdutoDepartamento>();
	private List<ProdutoSolicitacaoEntrada> entradas = new ArrayList<ProdutoSolicitacaoEntrada>(); 
	private List<ProdutoSolicitacaoSaida> saidas = new ArrayList<ProdutoSolicitacaoSaida>();
	private List<Item> itens = new ArrayList<Item>();
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public float getQtdEmEstoque() {
		return qtdEmEstoque;
	}
	public void setQtdEmEstoque(float qtdEmEstoque) {
		this.qtdEmEstoque = qtdEmEstoque;
	}
	public float getQtdMinima() {
		return qtdMinima;
	}
	public void setQtdMinima(float qtdMinima) {
		this.qtdMinima = qtdMinima;
	}
	public float getQtdMaxima() {
		return qtdMaxima;
	}
	public void setQtdMaxima(float qtdMaxima) {
		this.qtdMaxima = qtdMaxima;
	}
	public float getQtdSeguranca() {
		return qtdSeguranca;
	}
	public void setQtdSeguranca(float qtdSeguranca) {
		this.qtdSeguranca = qtdSeguranca;
	}
	public float getConsumo() {
		return consumo;
	}
	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}
	public String getConsumoPrevisto() {
		return consumoPrevisto;
	}
	public void setConsumoPrevisto(String consumoPrevisto) {
		this.consumoPrevisto = consumoPrevisto;
	}
	public List<SolicitacaoProdutoFornecedor> getQtdPedidoFornecedor() {
		return qtdPedidoFornecedor;
	}
	public void setQtdPedidoFornecedor(List<SolicitacaoProdutoFornecedor> qtdPedidoFornecedor) {
		this.qtdPedidoFornecedor = qtdPedidoFornecedor;
	}
	public List<SolicitacaoProdutoDepartamento> getQtdPedidoDepartamento() {
		return qtdPedidoDepartamento;
	}
	public void setQtdPedidoDepartamento(List<SolicitacaoProdutoDepartamento> qtdPedidoDepartamento) {
		this.qtdPedidoDepartamento = qtdPedidoDepartamento;
	}
	public void adicionarSolicitacaoFornecedor(SolicitacaoProdutoFornecedor sol){
		qtdPedidoFornecedor.add(sol);
	}
	public void removerSolicitacaoFornecedor(SolicitacaoProdutoFornecedor sol){
		qtdPedidoFornecedor.remove(sol);
	}
	public void removerSolFornecedorPorIndice(int index){
		qtdPedidoFornecedor.remove(index);
	}
	public void adicionarSolicitacaoDepartamento(SolicitacaoProdutoDepartamento sol){
		qtdPedidoDepartamento.add(sol);
	}
	public void removerSolicitacaoDepartamento(SolicitacaoProdutoDepartamento sol){
		qtdPedidoDepartamento.remove(sol);
	}
	public void removerSolDepartamentoPorIndice(int index){
		qtdPedidoDepartamento.remove(index);
	}
	public void adicionaItem(Item i){
		itens.add(i);
	}
	public void removeItem(Item i){
		itens.remove(i);
	}
	public void removeItemPorIndice(int i){
		itens.remove(i);
	}
	public float qtdEmEstoque(){
		float qtd = 0;
		for(Item i : itens){
			if(i.verSaida()){
				qtd++;
			}
		}
		return qtd;
	}
	public float qtdTotal(){
		return itens.size();
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	public List<ProdutoSolicitacaoEntrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(List<ProdutoSolicitacaoEntrada> entradas) {
		this.entradas = entradas;
	}
	public List<ProdutoSolicitacaoSaida> getSaidas() {
		return saidas;
	}
	public void setSaidas(List<ProdutoSolicitacaoSaida> saidas) {
		this.saidas = saidas;
	}
	public void adicionaProdutoEntrada(ProdutoSolicitacaoEntrada i){
		entradas.add(i);
	}
	public void removeProdutoEntrada(ProdutoSolicitacaoEntrada i){
		entradas.remove(i);
	}
	public void removeEntradaPorIndice(int i){
		entradas.remove(i);
	}
	public void adicionaProdutoSaida(ProdutoSolicitacaoSaida i){
		saidas.add(i);
	}
	public void removeProdutoSaida(ProdutoSolicitacaoSaida i){
		saidas.remove(i);
	}
	public void removeSaidaPorIndice(int i){
		saidas.remove(i);
	}
	public float tempoPorFornecedor(Fornecedor f){
		float tempo = f.calcQtdTempo(this);
		setMediaEntrega(tempo);
		return tempo;
	}	
	public float getMediaEntrega(Fornecedor f) {
		tempoPorFornecedor(f);
		return mediaEntrega;
	}
	public void setMediaEntrega(float mediaEntrega) {
		this.mediaEntrega = mediaEntrega;
	}
	public float qtdPorFornecedor(Fornecedor f){
		return f.qtdEntregueProduto(this);
	}
	public float qtdPedidosPorProduto(Fornecedor f){
		return f.getQtdPedidosPorProduto(this);
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}	
}