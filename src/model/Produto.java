package model;

public class Produto {
	private int id;
	private String nome;
	private String uso;
	private float qtdEmEstoque; // quantidade em estoque
	private float qtdMinima;
	private float qtdMaxima;
	private float qtdSeguranca;
	private float consumo; // quantidade ja utilizada
	private String consumoPrevisto;
	private float mediaEntrega; // calcular o tempo medio de entrega
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
	public float getMediaEntrega() {
		return mediaEntrega;
	}
	public void setMediaEntrega(float mediaEntrega) {
		this.mediaEntrega = mediaEntrega;
	}
}
