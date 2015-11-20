package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoProdutoDepartamento;

public class SolicitacaoProdutoDepartamentoDAO {
	private Connection c;
	public SolicitacaoProdutoDepartamentoDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(SolicitacaoProdutoDepartamento e) {
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
			e1.printStackTrace();
		}
	}
	
	public void atualizar(SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "UPDATE solicitacao_Produto_Departamento SET quantidade = ? WHERE id_Produto = ? AND id_Solicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setInt(2, e.getIdProduto());
			ps.setInt(3, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Departamento WHERE id_Produto = ? AND id_Solicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	private String getSql(SolicitacaoProdutoDepartamento d){
		int ver = 0;
		String sql = "SELECT id_Produto ,id_Solicitacao, getQuantidade FROM solicitacao_Produto_Departamento ";
		if(d.getIdProduto() != 0 ){
			sql += "WHERE id_Produto LIKE '%" +d.getIdProduto()+"%' ";
			ver++;
		}		
		if(d.getIdSolicitacao() != 0){
			if(ver>0){
				sql += "AND id_Solicitacao LIKE '%"+d.getIdSolicitacao()+"%' ";
			}
			else{
				sql += "WHERE id_Solicitacao LIKE '%"+d.getIdSolicitacao()+"%' ";	
				ver++;
			}
		}
		if(d.getQuantidade() != 0){
			if(ver>0){
				sql += "AND quantidade LIKE '%"+d.getQuantidade()+"%' ";
			}
			else{
				sql += "WHERE quantidade LIKE '%"+d.getQuantidade()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<SolicitacaoProdutoDepartamento> cons(SolicitacaoProdutoDepartamento d) throws SQLException {
		List<SolicitacaoProdutoDepartamento> lista = new ArrayList<SolicitacaoProdutoDepartamento>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
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
	}
}
