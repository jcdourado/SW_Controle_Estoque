package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Item;

public class ControleItem implements TableModel{
	List<Item> itens = new ArrayList<Item>();

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
		case 2: return i.getIdSaida();
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
