package model;

import java.util.ArrayList;
import java.util.List;

public class Tipo {
	private int id;
	private String nome;
	private List<Tipo> restricoes = new ArrayList<Tipo>();
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
	public List<Tipo> getRestricoes() {
		return restricoes;
	}
	public void setRestricoes(List<Tipo> restricoes) {
		this.restricoes = restricoes;
	}
	public void adiciona(Tipo t){
		restricoes.add(t);
	}
	public void removeTipo(Tipo t){
		restricoes.remove(t);
	}
	public void removePorNumero(int i){
		restricoes.remove(i);
	}
}
