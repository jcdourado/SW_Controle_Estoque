package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProdutoSolicitacaoEntrada;

public class ProSolEntDAO {
	private Connection c;
	
	public ProSolEntDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Entrada (quantidade, uso, "
					+ "idProduto, idEntrada, idSolicitacao) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdEntrada());
			ps.setInt(5, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "UPDATE produto_Solicitacao_Entrada SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "idEntrada = ?, idSolicitacao = ? WHERE codSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdEntrada());
			ps.setInt(5, e.getIdSolicitacao());
			ps.setInt(6, e.getCodSolicitacaoEntrada());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM produto_Solicitacao_Entrada WHERE codSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codSolicitacaoEntrada) + 1 AS proximo_id FROM produto_Solicitacao_Entrada";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
	private String getSql(ProdutoSolicitacaoEntrada d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codSolicitacaoEntrada, quantidade , uso , idProduto , "
					+ "idEntrada, idSolicitacao  FROM produto_Solicitacao_Entrada ");
		if(d.getCodSolicitacaoEntrada() != 0 ){
			sql.append("WHERE codSolicitacaoEntrada LIKE '%" +d.getCodSolicitacaoEntrada()+"%' ");
			ver++;
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
		if(d.getUso() != null){
			if(ver>0){
				sql.append("AND uso LIKE '%"+d.getUso()+"%' ");
			}
			else{
				sql.append("WHERE uso LIKE '%"+d.getUso()+"%' ");	
				ver++;
			}
		}
		if(d.getIdProduto() != 0){
			if(ver>0){
				sql.append("AND idProduto LIKE '%"+d.getIdProduto()+"%' ");
			}
			else{
				sql.append("WHERE idProduto LIKE '%"+d.getIdProduto()+"%' ");	
				ver++;
			}
		}
		if(d.getIdEntrada() != 0){
			if(ver>0){
				sql.append("AND idEntrada LIKE '%"+d.getIdEntrada()+"%' ");
			}
			else{
				sql.append("WHERE idEntrada LIKE '%"+d.getIdEntrada()+"%' ");	
				ver++;
			}
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
		return sql.toString();
	}
		
	public List<ProdutoSolicitacaoEntrada> cons(ProdutoSolicitacaoEntrada d) throws SQLException {
		List<ProdutoSolicitacaoEntrada> lista = new ArrayList<ProdutoSolicitacaoEntrada>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ProdutoSolicitacaoEntrada dp = new ProdutoSolicitacaoEntrada();
			dp.setCodSolicitacaoEntrada(rs.getInt("codSolicitacaoEntrada"));
			dp.setQuantidade(rs.getFloat("quantidade"));
			dp.setUso(rs.getString("uso"));
			dp.setIdProduto(rs.getInt("idProduto"));
			dp.setIdEntrada(rs.getInt("idEntrada"));
			dp.setIdSolicitacao(rs.getInt("idSolicitacao"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}
