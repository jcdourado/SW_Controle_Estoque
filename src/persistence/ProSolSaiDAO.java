package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProdutoSolicitacaoSaida;

public class ProSolSaiDAO {
	private Connection c;
	
	public ProSolSaiDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(ProdutoSolicitacaoSaida e) {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Saida (quantidade, uso, "
					+ "idPoduto, idSaida, idSolicitacao) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdSaida());
			ps.setInt(5, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(ProdutoSolicitacaoSaida e) {
		try {
			String sql = "UPDATE produto_Solicitacao_Saida SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "idEntrada = ?, idSolicitacao = ? WHERE codSolicitacaoSaida = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdSaida());
			ps.setInt(5, e.getIdSolicitacao());
			ps.setInt(6, e.getCodSolicitacaoSaida());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM produto_Solicitacao_Saida WHERE codcodSolicitacaoSaida = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codSolicitacaoSaida) + 1 AS proximo_id FROM produto_Solicitacao_Saida";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}	
	private String getSql(ProdutoSolicitacaoSaida d){
		int ver = 0;
		String sql = "SELECT codSolicitacaoSaida, quantidade , uso , codSaida , "
					+ "codProduto, codSolicitacao  FROM produto_Solicitacao_Saida ";
		if(d.getCodSolicitacaoSaida() != 0 ){
			sql += "WHERE codSolicitacaoSaida LIKE '%" +d.getCodSolicitacaoSaida()+"%' ";
			ver++;
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
		if(d.getUso() != null){
			if(ver>0){
				sql += "AND uso LIKE '%"+d.getUso()+"%' ";
			}
			else{
				sql += "WHERE uso LIKE '%"+d.getUso()+"%' ";	
				ver++;
			}
		}
		if(d.getIdProduto() != 0){
			if(ver>0){
				sql += "AND codProduto LIKE '%"+d.getIdProduto()+"%' ";
			}
			else{
				sql += "WHERE codProduto LIKE '%"+d.getIdProduto()+"%' ";	
				ver++;
			}
		}
		if(d.getIdSaida() != 0){
			if(ver>0){
				sql += "AND codSaida LIKE '%"+d.getIdSaida()+"%' ";
			}
			else{
				sql += "WHERE codSaida LIKE '%"+d.getIdSaida()+"%' ";	
				ver++;
			}
		}
		if(d.getIdSolicitacao() != 0){
			if(ver>0){
				sql += "AND codSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ";
			}
			else{
				sql += "WHERE codSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<ProdutoSolicitacaoSaida> cons(ProdutoSolicitacaoSaida d) throws SQLException {
		List<ProdutoSolicitacaoSaida> lista = new ArrayList<ProdutoSolicitacaoSaida>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ProdutoSolicitacaoSaida dp = new ProdutoSolicitacaoSaida();
			dp.setCodSolicitacaoSaida(rs.getInt("codSolicitacaoSaida"));
			dp.setQuantidade(rs.getFloat("quantidade"));
			dp.setUso(rs.getString("uso"));
			dp.setIdProduto(rs.getInt("codProduto"));
			dp.setIdSaida(rs.getInt("codSaida"));
			dp.setIdSolicitacao(rs.getInt("codSolicitacao"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}
