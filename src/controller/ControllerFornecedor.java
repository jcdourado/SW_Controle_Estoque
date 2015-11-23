package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Fornecedor;

public class ControllerFornecedor implements TableModel{
	List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return String.class;
		case 2: return Integer.class;
		case 3: return String.class;
		case 4: return String.class;
		case 5: return String.class;
		case 6: return String.class;
		case 7: return String.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Rua";
		case 2: return "Número";
		case 3: return "Bairro";
		case 4: return "Cidade";
		case 5: return "Estado";
		case 6: return "Nome";
		case 7: return "Telefone";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return fornecedores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Fornecedor f = fornecedores.get(rowIndex);
		switch(columnIndex){
		case 0: return f.getId();
		case 1: return f.getRua();
		case 2: return f.getNumero();
		case 3: return f.getBairro();
		case 4: return f.getCidade();
		case 5: return f.getEstado();
		case 6: return f.getNome();
		case 7: return f.getTel();
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
