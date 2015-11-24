package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import persistence.RestricaoDAO;
import utilities.EstoqueException;
import model.Tipo;

public class ControllerTipoRestricao implements TableModel{

	private List<Tipo> tiposRestringidos = new ArrayList<Tipo>(); 
	private RestricaoDAO dao;
	
	public void adicionar(Tipo t1, Tipo t2) throws EstoqueException{
		dao = new RestricaoDAO();
		dao.adicionar(t1, t2);
		dao.cons(t1);
	}
	public void remover(Tipo t1, Tipo t2) throws EstoqueException{
		dao = new RestricaoDAO();
		dao.remove(t1, t2);
		dao.cons(t1);
	}
	public List<Tipo> consultar(Tipo t1) throws EstoqueException{
		dao = new RestricaoDAO();
		tiposRestringidos = dao.cons(t1);
		return tiposRestringidos;
	}

	public List<Tipo> getTipos(){
		return tiposRestringidos;
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
		return tiposRestringidos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tipo t = tiposRestringidos.get(rowIndex);
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
