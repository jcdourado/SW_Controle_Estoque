package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Departamento;
import persistence.DepartamentoDAO;
import utilities.EstoqueException;

public class ControllerDepartamento implements TableModel{
	List<Departamento> departamentos = new ArrayList<Departamento>();
	DepartamentoDAO dao;

	public void adicionar(Departamento d) throws EstoqueException{
		dao = new DepartamentoDAO();
		dao.adicionar(d);
		consultar(new Departamento());
	}
	public void atualizar(Departamento d) throws EstoqueException{
		dao = new DepartamentoDAO();
		dao.atualizar(d);
		consultar(new Departamento());
	}
	public void remover(Departamento d) throws EstoqueException{
		dao = new DepartamentoDAO();
		dao.remove(d.getId());
		consultar(new Departamento());	
	}
	public List<Departamento> consultar(Departamento d) throws EstoqueException{
		dao = new DepartamentoDAO();
		departamentos = dao.cons(d);
		return departamentos;
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return String.class;
		case 4: return String.class;
		case 5: return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Nome";
		case 2: return "Andar";
		case 3: return "Prédio";
		case 4: return "Telefone";
		case 5: return "Código Responsável";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return departamentos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Departamento d = departamentos.get(rowIndex);
		switch(columnIndex){
		case 0: return d.getId();
		case 1: return d.getNome();
		case 2: return d.getAndar();
		case 3: return d.getPredio();
		case 4: return d.getTelefone();
		case 5: return d.getCodResponsavel();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

}
