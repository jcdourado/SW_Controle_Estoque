package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Saida;
import persistence.SaidaDAO;
import utilities.EstoqueException;

public class ControllerSaida implements TableModel{
	private List<Saida> saidas = new ArrayList<Saida>();
	private SaidaDAO dao;

	public void adicionar(Saida d) throws EstoqueException{
		dao = new SaidaDAO();
		dao.adicionar(d);
		consultar(new Saida());
	}
	public void atualizar(Saida d) throws EstoqueException{
		dao = new SaidaDAO();
		dao.atualizar(d);
		consultar(new Saida());
	}
	public void remover(Saida d) throws EstoqueException{
		dao = new SaidaDAO();
		dao.remover(d.getIdSaida());
		consultar(new Saida());	
	}
	public List<Saida> consultar(Saida d) throws EstoqueException{
		dao = new SaidaDAO();
		saidas = dao.cons(d);
		return saidas;
	}

	public List<Saida> getSaidas(){
		return saidas;
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return Date.class;
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
		case 0: return "C�digo";
		case 1: return "Data";
		case 2: return "Descri��o";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return saidas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Saida s = saidas.get(rowIndex);
		switch(columnIndex){
		case 0: return s.getIdSaida();
		case 1: return s.getData();
		case 2: return s.getDescricao();
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
