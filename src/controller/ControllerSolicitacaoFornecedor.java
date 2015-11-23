package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.SolicitacaoFornecedor;

public class ControllerSolicitacaoFornecedor implements TableModel{
	List<SolicitacaoFornecedor> solFornecedores = new ArrayList<SolicitacaoFornecedor>();

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return Integer.class;
		case 2: return Date.class;
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
		case 1: return "Codigo Fornecedor";
		case 2: return "Data";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return solFornecedores.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SolicitacaoFornecedor solForn = solFornecedores.get(rowIndex);
		switch(columnIndex){
		case 0: return solForn.getId();
		case 1: return solForn.getIdFornecedor();
		case 2: return solForn.getData();
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