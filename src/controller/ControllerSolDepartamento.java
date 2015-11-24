package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.SolicitacaoProdutoDepartamento;
import persistence.SolicitacaoProdutoDepartamentoDAO;
import utilities.EstoqueException;

public class ControllerSolDepartamento implements TableModel{
	private List<SolicitacaoProdutoDepartamento> sol = new  ArrayList<SolicitacaoProdutoDepartamento>();
	private SolicitacaoProdutoDepartamentoDAO dao;
	
	public void adicionar(SolicitacaoProdutoDepartamento sPro) throws EstoqueException{
		dao = new SolicitacaoProdutoDepartamentoDAO();
		dao.adicionar(sPro);
		SolicitacaoProdutoDepartamento t = new SolicitacaoProdutoDepartamento();
		t.setIdSolicitacao(sPro.getIdSolicitacao());
		consultar(t);
	}
	public void remover(SolicitacaoProdutoDepartamento sPro) throws EstoqueException{
		dao = new SolicitacaoProdutoDepartamentoDAO();
		dao.remove(sPro);
		SolicitacaoProdutoDepartamento t = new SolicitacaoProdutoDepartamento();
		t.setIdSolicitacao(sPro.getIdSolicitacao());
		consultar(t);
		
	}
	public List<SolicitacaoProdutoDepartamento> consultar(SolicitacaoProdutoDepartamento sPro) throws EstoqueException{
		dao = new SolicitacaoProdutoDepartamentoDAO();
		sol =  dao.cons(sPro);
		return sol;
	}
	public List<SolicitacaoProdutoDepartamento> getSol() throws EstoqueException{
		return sol;
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return Integer.class;
		case 2: return Float.class;
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
		case 0: return "Código Produto";
		case 1: return "Código Soliticação";
		case 2: return "Quantidade";
		}
		return "";	
	}

	@Override
	public int getRowCount() {
		return sol.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SolicitacaoProdutoDepartamento solF = sol.get(rowIndex);
		switch(columnIndex){
		case 0: return solF.getIdProduto();
		case 1: return solF.getIdSolicitacao();
		case 2: return solF.getQuantidade();
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
