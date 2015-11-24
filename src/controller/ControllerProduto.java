package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Produto;
import persistence.ProdutoDAO;
import utilities.EstoqueException;

public class ControllerProduto implements TableModel{
	private List<Produto> produtos = new ArrayList<Produto>();
	private ProdutoDAO dao;

	public void adicionar(Produto d) throws EstoqueException{
		dao = new ProdutoDAO();
		dao.adicionar(d);
		consultar(new Produto());
	}
	public void atualizar(Produto d) throws EstoqueException{
		dao = new ProdutoDAO();
		dao.atualizar(d);
		consultar(new Produto());
	}
	public void remover(Produto d) throws EstoqueException{
		dao = new ProdutoDAO();
		dao.remover(d.getId());
		consultar(new Produto());	
	}
	public List<Produto> consultar(Produto d) throws EstoqueException{
		dao = new ProdutoDAO();
		produtos = dao.cons(d);
		return produtos;
	}
	public List<Produto> getProdutos(){
		return produtos;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0: return Integer.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return Float.class;
		case 4: return Float.class;
		case 5: return Float.class;
		case 6: return String.class;
		case 7: return Float.class;
		case 8: return Float.class;
		case 9: return Integer.class;
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex){
		case 0: return "Código";
		case 1: return "Nome";
		case 2: return "Uso";
		case 3: return "Quantidade Miníma";
		case 4: return "Quantidade Segurança";
		case 5: return "Quantidade Máxima";
		case 6: return "Consumo Previsto";
		case 7: return "Preço";
		case 8: return "Peso";
		case 9: return "Código Tipo";
		}
		return "";
	}

	@Override
	public int getRowCount() {
		return produtos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto p = produtos.get(rowIndex);
		switch(columnIndex){
		case 0: return p.getId();
		case 1: return p.getNome();
		case 2: return p.getUso();
		case 3: return p.getQtdMinima();
		case 4: return p.getQtdSeguranca();
		case 5: return p.getQtdMaxima();
		case 6: return p.getConsumoPrevisto();
		case 7: return p.getPreco();
		case 8: return p.getPeso();
		case 9: return p.getTipo();
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
