package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Entrada;

public class ControllerEntrada implements TableModel{
	List<Entrada> entradas = new ArrayList<Entrada>();

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return Date.class;
		case 2: return String.class;
		case 3: return String.class;
		case 4: return Date.class;
		case 5: return Float.class;
		case 6: return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Data";
		case 2: return "Tipo de Transferência";
		case 3: return "NFE";
		case 4: return "Data Emissão NFE";
		case 5: return "Tempo";
		case 6: return "Código Fornecedor";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return entradas.size();
	}

	@Override
	public Object getValueAt(int rowIdex, int columnIndex) {
		Entrada e = entradas.get(rowIdex);
		switch(columnIndex){
		case 0: return e.getIdEntrada();
		case 1: return e.getData();
		case 2: return e.getTipoTransf();
		case 3: return e.getNFE();
		case 4: return e.getDataEmissarNFE();
		case 5: return e.getTempo();
		case 6: return e.getIdFornecedor();
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
