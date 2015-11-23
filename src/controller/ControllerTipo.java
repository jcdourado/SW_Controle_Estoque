package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Tipo;
import persistence.TipoDAO;
import utilities.EstoqueException;

public class ControllerTipo implements TableModel{
	List<Tipo> tipos = new ArrayList<Tipo>();
	TipoDAO dao;
	
	public void adicionar(Tipo t) throws EstoqueException{
		dao = new TipoDAO();
		dao.adicionar(t);
		consultar(new Tipo());
	}
	public void atualizar(Tipo t)throws EstoqueException{
		dao = new TipoDAO();
		dao.atualizar(t);	
		consultar(new Tipo());	
	}
	public void remover(Tipo t)throws EstoqueException{
		dao = new TipoDAO();
		dao.remove(t.getId());
		consultar(new Tipo());
	}
	public List<Tipo> consultar(Tipo t)throws EstoqueException{
		dao = new TipoDAO();
		tipos = dao.cons(t);
		return (tipos);
	}	
	
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Nome";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return tipos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tipo t = tipos.get(rowIndex);
		switch(columnIndex){
		case 0: return t.getId();
		case 1: return t.getNome();
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
