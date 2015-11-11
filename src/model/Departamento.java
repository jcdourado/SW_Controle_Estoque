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
	public float getMediaConsumo() {
		return mediaConsumo;
	}
	public void setMediaConsumo(float mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}
}