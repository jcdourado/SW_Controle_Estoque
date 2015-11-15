package model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	private int id;
	private String nome;
	private String andar;
	private String predio;
	private String telefone;
	private float mediaConsumo; // fazer a media
	private float consumoGeral;
	private int codResponsavel;
	private List<SolicitacaoDepartamento> solicitacoes = new ArrayList<SolicitacaoDepartamento>();
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
	public String getAndar() {
		return andar;
	}
	public void setAndar(String andar) {
		this.andar = andar;
	}
	public String getPredio() {
		return predio;
	}
	public void setPredio(String predio) {
		this.predio = predio;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getCodResponsavel() {
		return codResponsavel;
	}
	public void setCodResponsavel(int codResponsavel) {
		this.codResponsavel = codResponsavel;
	}
	public List<SolicitacaoDepartamento> getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(List<SolicitacaoDepartamento> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	public void adiciona(SolicitacaoDepartamento s){
		solicitacoes.add(s);
	}
	public void removeSolicitacao(SolicitacaoDepartamento s){
		solicitacoes.remove(s);
	}
	public void removePorIndice(int i){
		solicitacoes.remove(i);
	}
	public float calcConsumoGeral(){
		float qtd = 0;
		for(SolicitacaoDepartamento sol : solicitacoes){
			List<SolicitacaoProdutoDepartamento> solProduto = (ArrayList<SolicitacaoProdutoDepartamento>)sol.getSoliticacoes();
			for(SolicitacaoProdutoDepartamento solFinal : solProduto){
				qtd += solFinal.getQuantidade();
			}
		}
		setConsumoGeral(qtd);
		return qtd;
	}
	public float calcConsumoProduto(Produto p){
		float qtd = 0;
		for(SolicitacaoDepartamento sol : solicitacoes){
			List<SolicitacaoProdutoDepartamento> solProduto = (ArrayList<SolicitacaoProdutoDepartamento>)sol.getSoliticacoes();
			for(SolicitacaoProdutoDepartamento solFinal : solProduto){
				if(p.getId() == solFinal.getIdProduto()){
					qtd += solFinal.getQuantidade();
				}
			}
		}
		setConsumoGeral(qtd);
		return qtd;
	}
	public float calcQtdRecebidaPorProduto(Produto p){
		float total = 0;
		for(SolicitacaoDepartamento sol :solicitacoes){
			for(ProdutoSolicitacaoSaida saidas: sol.getRecebidos()){
				if(saidas.getIdProduto() == p.getId()){
					total += saidas.getQuantidade();
				}
			}
		}
		return total;
	}
	public float calcQtdRecebidaGeral(){
		float total = 0;
		for(SolicitacaoDepartamento sol :solicitacoes){
			for(ProdutoSolicitacaoSaida saidas: sol.getRecebidos()){
				total += saidas.getQuantidade();
			}
		}
		return total;		
	}
	public float getConsumoGeral() {
		calcConsumoGeral();
		return consumoGeral;
	}
	public void setConsumoGeral(float consumoGeral) {
		this.consumoGeral = consumoGeral;
	}
	public float getMediaConsumo() {
		return mediaConsumo;
	}
	public void setMediaConsumo(float mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}
	public float getRestante(Produto p){
		int qtd = 0;
		for(SolicitacaoDepartamento sol: solicitacoes){
			for(SolicitacaoProdutoDepartamento solProduto:sol.getSoliticacoes()){
				if(solProduto.getIdProduto() == p.getId()){
					qtd += sol.getQtdRestante(p);
				}
			}
		}
		return qtd;
	}
	public boolean restaAlgoGeral(){
		for(SolicitacaoDepartamento sol :solicitacoes)
			if(sol.qtdRestanteGeral() > 0){
				return false;
			}
		return true;
	}
	public boolean restaAlgo(Produto p){
		if(getRestante(p) > 0){
			return false;
		}
		return true;
	}
	public float qtdRestanteGeral(){
		int rest = 0;
		for(SolicitacaoDepartamento sol : solicitacoes){
			rest+=sol.qtdRestanteGeral();
		}
		return rest;
	}	
	public float calcPesoGeral(){
		int preco = 0;
		for(SolicitacaoDepartamento sol: solicitacoes){
			preco += sol.calcPesoGeral();
		}
		return preco;
	}	
	public float calcPrecoGeral(){
		int preco = 0;
		for(SolicitacaoDepartamento sol: solicitacoes){
			preco += sol.calcPrecoGeral();
		}
		return preco;
	}	
	public float calcPesoPorProduto(Produto p){
		int preco = 0;
		for(SolicitacaoDepartamento sol: solicitacoes){
			preco += sol.calcPesoPorProduto(p);
		}
		return preco;
	}	
	public float calcPrecoPorProduto(Produto p){
		int preco = 0;
		for(SolicitacaoDepartamento sol: solicitacoes){
			preco += sol.calcPrecoPorProduto(p);
		}
		return preco;
	}
	public float calcPrecoPorSolicitacao(SolicitacaoDepartamento solDpto){
		int preco = 0;
		for(SolicitacaoProdutoDepartamento solProduto : solDpto.getSoliticacoes()){
			preco += solDpto.calcPrecoPorSolicitacaoProduto(solProduto, solProduto.getP());
		}
		return preco;
	}
	public float calcPesoPorSolicitacao(SolicitacaoDepartamento solDpto){
		int preco = 0;
		for(SolicitacaoProdutoDepartamento solProduto : solDpto.getSoliticacoes()){
			preco += solDpto.calcPesoPorSolicitacaoProduto(solProduto, solProduto.getP());
		}
		return preco;
	}
	public float calcPrecoPorSolicitacaoProduto(SolicitacaoDepartamento solDpto, Produto p){
		int preco = 0;
		for(SolicitacaoProdutoDepartamento solProduto : solDpto.getSoliticacoes()){
			preco += solDpto.calcPrecoPorSolicitacaoProduto(solProduto, p);
		}
		return preco;
	}
	public float calcPesoPorSolicitacaoProduto(SolicitacaoDepartamento solDpto, Produto p){
		int preco = 0;
		for(SolicitacaoProdutoDepartamento solProduto : solDpto.getSoliticacoes()){
			preco += solDpto.calcPesoPorSolicitacaoProduto(solProduto, p);
		}
		return preco;
	}
}