package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Item;
import persistence.ItemDAO;
import utilities.EstoqueException;

public class ControleItem implements TableModel{
	private List<Item> itens = new ArrayList<Item>();
	private ItemDAO dao;

	public void adicionar(Item d) throws EstoqueException{
		dao = new ItemDAO();
		if(d.getIdSaida() != 0){
			dao.adicionar(d);
		}
		else{
			dao.adicionarSemSaida(d);
		}
		consultar(new Item());
	}
	public void atualizar(Item d) throws EstoqueException{
		dao = new ItemDAO();
		if(d.getIdSaida() != 0){
			dao.atualizar(d);
		}
		else{
			dao.atualizarSemSaida(d);
		}
		consultar(new Item());
	}
	public void remover(Item d) throws EstoqueException{
		dao = new ItemDAO();
		dao.remove(d.getIdItem());
		consultar(new Item());	
	}
	public List<Item> consultar(Item d) throws EstoqueException{
		dao = new ItemDAO();
		itens = dao.cons(d);
		return itens;
	}
	public List<Item> getItens(){
		return itens;
	}
	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch(col){
		case 0: return Integer.class;
		case 1: return Integer.class;
		case 2: return Integer.class;
		case 3:	return Integer.class;	
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int col) {
		switch(col){
		case 0: return "Código Item";
		case 1: return "Código Produto";
		case 2: return "Código Saída";
		case 3: return "Código Entrada";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return itens.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Item i = itens.get(row);
		switch(col){
		case 0: return i.getIdItem();
		case 1: return i.getIdProduto();
		case 2: if(i.getIdSaida() == 0){
			return "";
		}
			return i.getIdSaida();
		case 3: return i.getIdEntrada();
		}
		return "";
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

}
