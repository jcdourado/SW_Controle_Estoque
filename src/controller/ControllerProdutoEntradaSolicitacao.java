package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Item;
import model.ProdutoSolicitacaoEntrada;
import persistence.ProSolEntDAO;
import utilities.EstoqueException;

public class ControllerProdutoEntradaSolicitacao implements TableModel{
	List<ProdutoSolicitacaoEntrada> solEnt = new ArrayList<ProdutoSolicitacaoEntrada>();
	ProSolEntDAO dao;
	
	public void adicionar(ProdutoSolicitacaoEntrada pSol) throws EstoqueException{
		dao = new ProSolEntDAO();
		dao.adicionar(pSol);
		ControleItem ctrItem = new ControleItem();
		for(int i = 0; i < pSol.getQuantidade() ; i ++){
			Item d = new Item();
			d.setIdEntrada(pSol.getIdEntrada());
			d.setIdProduto(pSol.getIdProduto());
			ctrItem.adicionar(d);
		}
		ProdutoSolicitacaoEntrada p = new ProdutoSolicitacaoEntrada();
		p.setIdSolicitacao(pSol.getIdSolicitacao());
		consultar(p);
	}
	public void remover(ProdutoSolicitacaoEntrada pSol) throws EstoqueException{
		dao = new ProSolEntDAO();
		dao.remove(pSol.getCodSolicitacaoEntrada());
		ProdutoSolicitacaoEntrada p = new ProdutoSolicitacaoEntrada();
		p.setIdSolicitacao(pSol.getIdSolicitacao());
		consultar(p);
	}
	public List<ProdutoSolicitacaoEntrada> consultar(ProdutoSolicitacaoEntrada pSol)throws EstoqueException{
		dao = new ProSolEntDAO();
		solEnt = dao.cons(pSol);
		return solEnt;
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return Float.class;
		case 2: return String.class;
		case 3: return Integer.class;
		case 4: return Integer.class;
		case 5: return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Quantidade";
		case 2: return "Uso";
		case 3: return "Código Produto";
		case 4: return "Código Entrada";
		case 5: return "Código Solicitação";
		}
		return "";	
	}

	@Override
	public int getRowCount() {
		return solEnt.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoSolicitacaoEntrada solF = solEnt.get(rowIndex);
		switch(columnIndex){
		case 0: return solF.getCodSolicitacaoEntrada();
		case 1: return solF.getQuantidade();
		case 2: return solF.getUso();
		case 3: return solF.getIdProduto();
		case 4: return solF.getIdEntrada();
		case 5: return solF.getIdSolicitacao();
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
	public List<ProdutoSolicitacaoEntrada> getSol() {
		return solEnt;
	}
}
