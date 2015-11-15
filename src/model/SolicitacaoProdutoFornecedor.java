package model;

public class SolicitacaoProdutoFornecedor {
	private int idProduto;
	private int idSolicitacao;
	private float quantidade;
	private Produto p;
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getP() {
		return p;
	}
	public void setP(Produto p) {
		this.p = p;
	}
}