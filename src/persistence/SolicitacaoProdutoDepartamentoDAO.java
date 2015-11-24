package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoProdutoDepartamento;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class SolicitacaoProdutoDepartamentoDAO {
	private Connection c;
	public SolicitacaoProdutoDepartamentoDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(SolicitacaoProdutoDepartamento e) throws EstoqueException {
		try {
			String sql = "INSERT INTO solicitacao_Produto_Departamento (id_Produto, "
					+ "id_Solicitacao, quantidade) VALUES (?, ?, ?)";
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
	
	public void atualizar(SolicitacaoProdutoDepartamento e) throws EstoqueException {
		try {
			String sql = "UPDATE solicitacao_Produto_Departamento SET quantidade = ? WHERE id_Produto = ? AND id_Solicitacao = ?";
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
	
	public void remove(SolicitacaoProdutoDepartamento e) throws EstoqueException {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Departamento WHERE id_Produto = ? AND id_Solicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	private String getSql(SolicitacaoProdutoDepartamento d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_Produto ,id_Solicitacao, quantidade FROM solicitacao_Produto_Departamento ");
		if(d.getIdProduto() != 0 ){
			sql.append("WHERE id_Produto LIKE '%" +d.getIdProduto()+"%' ");
			ver++;
		}		
		if(d.getIdSolicitacao() != 0){
			if(ver>0){
				sql.append("AND id_Solicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");
			}
			else{
				sql.append("WHERE id_Solicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");	
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
		
	public List<SolicitacaoProdutoDepartamento> cons(SolicitacaoProdutoDepartamento d) throws EstoqueException {
		List<SolicitacaoProdutoDepartamento> lista = new ArrayList<SolicitacaoProdutoDepartamento>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SolicitacaoProdutoDepartamento dp = new SolicitacaoProdutoDepartamento();
				dp.setIdProduto(rs.getInt("id_Produto"));
				dp.setIdSolicitacao(rs.getInt("id_Solicitacao"));
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
