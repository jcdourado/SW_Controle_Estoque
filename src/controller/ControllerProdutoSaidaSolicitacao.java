package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Item;
import model.ProdutoSolicitacaoSaida;
import persistence.ProSolSaiDAO;
import utilities.EstoqueException;

public class ControllerProdutoSaidaSolicitacao implements TableModel{
	List<ProdutoSolicitacaoSaida> solEnt = new ArrayList<ProdutoSolicitacaoSaida>();
	ProSolSaiDAO dao;
	
	public List<ProdutoSolicitacaoSaida> getSol(){
		return solEnt;
	}
	public void adicionar(ProdutoSolicitacaoSaida pSol) throws EstoqueException{
		dao = new ProSolSaiDAO();
		dao.adicionar(pSol);
		ControleItem ctrItem = new ControleItem();
		Item i = new Item();
		i.setIdProduto(pSol.getIdProduto());
		List<Item> list = ctrItem.consultar(i);
		int cont = 0;
		for(Item novoItem : list){
			if(cont < pSol.getCodSolicitacaoSaida()){
				if(novoItem.verSaida()){
					novoItem.setIdSaida(pSol.getIdSaida());
					ctrItem.atualizar(novoItem);
				}
			}
		}
		ProdutoSolicitacaoSaida p = new ProdutoSolicitacaoSaida();
		p.setIdSolicitacao(pSol.getIdSolicitacao());
		consultar(p);
	}
	public void adicionarPorSaida(ProdutoSolicitacaoSaida pSol) throws EstoqueException{
		dao = new ProSolSaiDAO();
		dao.adicionar(pSol);
		ProdutoSolicitacaoSaida p = new ProdutoSolicitacaoSaida();
		p.setIdSaida(pSol.getIdSaida());
		consultar(p);
	}
	public void remover(ProdutoSolicitacaoSaida pSol) throws EstoqueException{
		dao = new ProSolSaiDAO();
		dao.remove(pSol.getCodSolicitacaoSaida());
		ProdutoSolicitacaoSaida p = new ProdutoSolicitacaoSaida();
		p.setIdSolicitacao(pSol.getIdSolicitacao());
		consultar(p);
	}
	public void removerPorSaida(ProdutoSolicitacaoSaida pSol) throws EstoqueException{
		dao = new ProSolSaiDAO();
		dao.remove(pSol.getCodSolicitacaoSaida());
		ProdutoSolicitacaoSaida p = new ProdutoSolicitacaoSaida();
		p.setIdSaida(pSol.getIdSaida());
		consultar(p);
	}
	public List<ProdutoSolicitacaoSaida> consultar(ProdutoSolicitacaoSaida pSol)throws EstoqueException{
		dao = new ProSolSaiDAO();
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
		case 4: return "Código Saida";
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
		ProdutoSolicitacaoSaida solF = solEnt.get(rowIndex);
		switch(columnIndex){
		case 0: return solF.getCodSolicitacaoSaida();
		case 1: return solF.getQuantidade();
		case 2: return solF.getUso();
		case 3: return solF.getIdProduto();
		case 4: return solF.getIdSaida();
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
}
