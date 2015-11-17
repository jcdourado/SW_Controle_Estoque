package model;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor {
	private int id;
	private String nome;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String tel;
	private float qtdPedidos;
	private float qtdPedidosPorProduto;
	private float qtdProdutosPedidosTotal;
	private float qtdProdutosEntregues;
	private float mediaTempo;
	private List<SolicitacaoFornecedor> solicitacoes = new ArrayList<SolicitacaoFornecedor>();
	private List<Entrada> entradas = new ArrayList<Entrada>();
		
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public int qtdPedidosFornecedor(){
		setQtdPedidos(solicitacoes.size());
		return solicitacoes.size();
	}
	public float qtdPedidosPorProduto(Produto produto){
		float qtd = 0;
		for(SolicitacaoFornecedor sol : solicitacoes){
			ArrayList<SolicitacaoProdutoFornecedor> p =  (ArrayList<SolicitacaoProdutoFornecedor>) sol.getSolicitacoes();
			for(SolicitacaoProdutoFornecedor solFornecedor : p){
				if(produto.getId() == solFornecedor.getIdProduto()){
					qtd += solFornecedor.getQuantidade();
				}
			}
		}
		setQtdPedidosPorProduto(qtd);
		return qtd;
	}
	public float qtdProdutosPedidosFornecedor(){
		float qtd = 0;
		for(SolicitacaoFornecedor sol : solicitacoes){
			ArrayList<SolicitacaoProdutoFornecedor> p =  (ArrayList<SolicitacaoProdutoFornecedor>) sol.getSolicitacoes();
			for(SolicitacaoProdutoFornecedor solFornecedor : p){
				qtd += solFornecedor.getQuantidade();
			}
		}
		setQtdProdutosPedidosTotal(qtd);
		return qtd;
	}
	public float getQtdPedidos() {
		qtdPedidosFornecedor();
		return qtdPedidos;
	}
	public void setQtdPedidos(float qtdPedidos) {
		this.qtdPedidos = qtdPedidos;
	}
	public float getQtdPedidosPorProduto(Produto p) {
		qtdPedidosPorProduto(p);
		return qtdPedidosPorProduto;
	}
	public void setQtdPedidosPorProduto(float qtdPedidosPorProduto) {
		this.qtdPedidosPorProduto = qtdPedidosPorProduto;
	}
	public float getQtdProdutosPedidosTotal() {
		qtdProdutosPedidosFornecedor();
		return qtdProdutosPedidosTotal;
	}
	public void setQtdProdutosPedidosTotal(float qtdProdutosPedidosTotal) {
		this.qtdProdutosPedidosTotal = qtdProdutosPedidosTotal;
	}
	public float calcQtdTempo(Produto p){
		float tempo = 0;
		int div = 0;
		for(Entrada ent : entradas){
			int cont = 0;
			ArrayList<ProdutoSolicitacaoEntrada> solEntrada = (ArrayList<ProdutoSolicitacaoEntrada>) ent.getEntregues();
			for(ProdutoSolicitacaoEntrada pFinal : solEntrada){
				if(cont == 0){
					if(pFinal.getIdProduto() == p.getId()){
						tempo = ent.getTempo()+tempo;
						cont++;
						div++;
					}
				}
			}
		}
		if(div > 0){
			tempo = tempo / div;
		}
		setMediaTempo(tempo);
		return tempo;
	}
	public float getMediaTempo(Produto p) {
		calcQtdTempo(p);
		return mediaTempo;
	}
	public void setMediaTempo(float mediaTempo) {
		this.mediaTempo = mediaTempo;
	}
	public float qtdEntregueTotal(){
		float res = 0;
		for(Entrada ent : entradas){
			ArrayList<ProdutoSolicitacaoEntrada> solEntrada = (ArrayList<ProdutoSolicitacaoEntrada>) ent.getEntregues();
			for(ProdutoSolicitacaoEntrada pFinal : solEntrada){
					res += pFinal.getQuantidade();
			}
		}
		setQtdProdutosEntregues(res);
		return res;
	}
	
	public float getQtdProdutosEntregues() {
		qtdEntregueTotal();
		return qtdProdutosEntregues;
	}
	public void setQtdProdutosEntregues(float qtdProdutosEntregues) {
		this.qtdProdutosEntregues = qtdProdutosEntregues;
	}
	public float qtdEntregueProduto(Produto p){
		float res = 0;
		for(Entrada ent : entradas){
			ArrayList<ProdutoSolicitacaoEntrada> solEntrada = (ArrayList<ProdutoSolicitacaoEntrada>) ent.getEntregues();
			for(ProdutoSolicitacaoEntrada pFinal : solEntrada){
					if(p.getId() == pFinal.getIdProduto()){
						res += pFinal.getQuantidade();
					}
			}
		}
		return res;
	}
	public float qtdRestante(Produto p){
		int qtd = 0;
		for(SolicitacaoFornecedor sol: solicitacoes){
			for(SolicitacaoProdutoFornecedor solProduto:sol.getSolicitacoes()){
				if(solProduto.getIdProduto() == p.getId()){
					qtd += sol.getQtdRestante(p);
				}
			}
		}
		return qtd;
	}
	public boolean restaAlgoGeral(){
		for(SolicitacaoFornecedor sol :solicitacoes)
			if(sol.qtdRestanteGeral() > 0){
				return false;
			}
		return true;
	}
	public boolean restaAlgo(Produto p){
		if(qtdRestante(p) > 0){
			return false;
		}
		return true;
	}
	public float qtdRestanteGeral(){
		int rest = 0;
		for(SolicitacaoFornecedor sol : solicitacoes){
			rest+=sol.qtdRestanteGeral();
		}
		return rest;
	}	
	public float calcPesoGeral(){
		int preco = 0;
		for(SolicitacaoFornecedor sol: solicitacoes){
			preco += sol.calcPesoGeral();
		}
		return preco;
	}	
	public float calcPrecoGeral(){
		int preco = 0;
		for(SolicitacaoFornecedor sol: solicitacoes){
			preco += sol.calcPrecoGeral();
		}
		return preco;
	}	
	public float calcPesoPorProduto(Produto p){
		int preco = 0;
		for(SolicitacaoFornecedor sol: solicitacoes){
			preco += sol.calcPesoPorProduto(p);
		}
		return preco;
	}	
	public float calcPrecoPorProduto(Produto p){
		int preco = 0;
		for(SolicitacaoFornecedor sol: solicitacoes){
			preco += sol.calcPrecoPorProduto(p);
		}
		return preco;
	}	
	public float calcPrecoPorSolicitacao(SolicitacaoFornecedor solForn){
		int preco = 0;
		for(SolicitacaoProdutoFornecedor solProduto : solForn.getSolicitacoes()){
			preco += solForn.calcPrecoPorSolicitacaoProduto(solProduto, solProduto.getP());
		}
		return preco;
	}
	public float calcPesoPorSolicitacao(SolicitacaoFornecedor solForn){
		int preco = 0;
		for(SolicitacaoProdutoFornecedor solProduto : solForn.getSolicitacoes()){
			preco += solForn.calcPesoPorSolicitacaoProduto(solProduto, solProduto.getP());
		}
		return preco;
	}
	public float calcPrecoPorSolicitacaoProduto(SolicitacaoFornecedor solForn, Produto p){
		int preco = 0;
		for(SolicitacaoProdutoFornecedor solProduto : solForn.getSolicitacoes()){
			preco += solForn.calcPrecoPorSolicitacaoProduto(solProduto, p);
		}
		return preco;
	}
	public float calcPesoPorSolicitacaoProduto(SolicitacaoFornecedor solForn, Produto p){
		int preco = 0;
		for(SolicitacaoProdutoFornecedor solProduto : solForn.getSolicitacoes()){
			preco += solForn.calcPesoPorSolicitacaoProduto(solProduto, p);
		}
		return preco;
	}
}