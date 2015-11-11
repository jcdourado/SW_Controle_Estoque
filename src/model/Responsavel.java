package model;

import java.util.ArrayList;
import java.util.List;

public class Responsavel {
	private int id;
	private String nome;
	private String tel;
	private List<Departamento> departamentos = new ArrayList<Departamento>();

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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	public void adiciona(Departamento d){
		departamentos.add(d);
	}
	public void removeDepartamento(Departamento d){
		departamentos.remove(d);
	}
	public void removePorIndice(int i){
		departamentos.remove(i);
	}
}