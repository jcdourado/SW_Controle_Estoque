package model;

public class ProdutoSolicitacaoSaida {
	private int codSolicitacaoSaida;
	private float quantidade;
	private String uso;
	private int idProduto;
	private int idSaida;
	private int idSolicitacao;
	private Produto p;
	public int getCodSolicitacaoSaida() {
		return codSolicitacaoSaida;
	}
	public void setCodSolicitacaoSaida(int codSolicitacaoSaida) {
		this.codSolicitacaoSaida = codSolicitacaoSaida;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdSaida() {
		return idSaida;
	}
	public void setIdSaida(int idSaida) {
		this.idSaida = idSaida;
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public Produto getP() {
		return p;
	}
	public void setP(Produto p) {
		this.p = p;
	}
}