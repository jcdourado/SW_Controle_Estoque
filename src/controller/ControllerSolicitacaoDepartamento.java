package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.SolicitacaoDepartamento;

public class ControllerSolicitacaoDepartamento implements TableModel{
	List<SolicitacaoDepartamento> solDepartamentos = new ArrayList<SolicitacaoDepartamento>();

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
		case 0: return "Código";
		case 1: return "Codigo Departamento";
		case 2: return "Data";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return solDepartamentos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SolicitacaoDepartamento solDep = solDepartamentos.get(rowIndex);
		switch(columnIndex){
		case 0: return solDep.getId();
		case 1: return solDep.getIdDepartamento();
		case 2: return solDep.getData();
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
