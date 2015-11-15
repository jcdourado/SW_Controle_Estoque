package model;

public class ProdutoSolicitacaoEntrada {
	private int codSolicitacaoEntrada;
	private float quantidade;
	private String uso;
	private int idProduto;
	private int idEntrada;
	private int idSolicitacao;
	private Produto p;
	public int getCodSolicitacaoEntrada() {
		return codSolicitacaoEntrada;
	}
	public void setCodSolicitacaoEntrada(int codSolicitacaoEntrada) {
		this.codSolicitacaoEntrada = codSolicitacaoEntrada;
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
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
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