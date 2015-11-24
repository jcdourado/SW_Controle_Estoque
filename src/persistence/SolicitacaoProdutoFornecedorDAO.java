package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoProdutoFornecedor;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class SolicitacaoProdutoFornecedorDAO {
	private Connection c;
	public SolicitacaoProdutoFornecedorDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(SolicitacaoProdutoFornecedor e) throws EstoqueException {
		try {
			String sql = "INSERT INTO solicitacao_Produto_Fornecedor (idProduto, "
					+ "idSolicitacao, quantidade) VALUES (?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSolicitacao());
			ps.setFloat(3, e.getQuantidade());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(SolicitacaoProdutoFornecedor e) throws EstoqueException {
		try {
			String sql = "UPDATE solicitacao_Produto_Fornecedor SET quantidade = ? WHERE idProduto = ? AND idSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setInt(2, e.getIdProduto());
			ps.setInt(3, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(SolicitacaoProdutoFornecedor e) throws EstoqueException {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Fornecedor WHERE idSolicitacao = ? AND idProduto = ? ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdSolicitacao());
			ps.setInt(2, e.getIdProduto());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	private String getSql(SolicitacaoProdutoFornecedor d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT idProduto ,idSolicitacao, quantidade FROM solicitacao_Produto_Fornecedor ");
		if(d.getIdProduto() != 0 ){
			sql.append("WHERE idProduto LIKE '%" +d.getIdProduto()+"%' ");
			ver++;
		}		
		if(d.getIdSolicitacao() != 0){
			if(ver>0){
				sql.append("AND idSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");
			}
			else{
				sql.append("WHERE idSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");	
				ver++;
			}
		}
		if(d.getQuantidade() != 0){
			if(ver>0){
				sql.append("AND quantidade LIKE '%"+d.getQuantidade()+"%' ");
			}
			else{
				sql.append("WHERE quantidade LIKE '%"+d.getQuantidade()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<SolicitacaoProdutoFornecedor> cons(SolicitacaoProdutoFornecedor d) throws EstoqueException {
		List<SolicitacaoProdutoFornecedor> lista = new ArrayList<SolicitacaoProdutoFornecedor>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SolicitacaoProdutoFornecedor dp = new SolicitacaoProdutoFornecedor();
				dp.setIdProduto(rs.getInt("idProduto"));
				dp.setIdSolicitacao(rs.getInt("idSolicitacao"));
				dp.setQuantidade(rs.getFloat("quantidade"));
				lista.add(dp);
			}
			rs.close();
			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
}
