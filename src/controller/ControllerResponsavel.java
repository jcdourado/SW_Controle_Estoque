package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Responsavel;
import persistence.ResponsavelDAO;
import utilities.EstoqueException;

public class ControllerResponsavel implements TableModel{
	List<Responsavel> responsaveis = new ArrayList<Responsavel>();
	ResponsavelDAO dao;

	public void adicionar(Responsavel d) throws EstoqueException{
		dao = new ResponsavelDAO();
		dao.adicionar(d);
		consultar(new Responsavel());
	}
	public void atualizar(Responsavel d) throws EstoqueException{
		dao = new ResponsavelDAO();
		dao.atualizar(d);
		consultar(new Responsavel());
	}
	public void remover(Responsavel d) throws EstoqueException{
		dao = new ResponsavelDAO();
		dao.remover(d.getId());
		consultar(new Responsavel());	
	}
	public List<Responsavel> consultar(Responsavel d) throws EstoqueException{
		dao = new ResponsavelDAO();
		responsaveis = dao.cons(d);
		return responsaveis;
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
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Nome";
		case 2: return "Telefone";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return responsaveis.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Responsavel r = responsaveis.get(rowIndex);
		switch(columnIndex){
		case 0: return r.getId();
		case 1: return r.getNome();
		case 2: return r.getTel();
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
