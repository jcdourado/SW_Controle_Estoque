package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.SolicitacaoDepartamento;
import persistence.SolicitacaoDepartamentoDAO;
import utilities.EstoqueException;

public class ControllerSolicitacaoDepartamento implements TableModel{
	private List<SolicitacaoDepartamento> solDepartamentos = new ArrayList<SolicitacaoDepartamento>();
	private SolicitacaoDepartamentoDAO dao;
	
	public void adicionar(SolicitacaoDepartamento t) throws EstoqueException{
		dao = new SolicitacaoDepartamentoDAO();
		dao.adicionar(t);
		consultar(new SolicitacaoDepartamento());
	}
	public void atualizar(SolicitacaoDepartamento t)throws EstoqueException{
		dao = new SolicitacaoDepartamentoDAO();
		dao.atualizar(t);	
		consultar(new SolicitacaoDepartamento());	
	}
	public void remover(SolicitacaoDepartamento t)throws EstoqueException{
		dao = new SolicitacaoDepartamentoDAO();
		dao.remove(t.getId());
		consultar(new SolicitacaoDepartamento());
	}
	public List<SolicitacaoDepartamento> consultar(SolicitacaoDepartamento t)throws EstoqueException{
		dao = new SolicitacaoDepartamentoDAO();
		solDepartamentos = dao.cons(t);
		return (solDepartamentos);
	}	
	
	public List<SolicitacaoDepartamento> getSolDepartamentos(){
		return solDepartamentos;
	}
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
