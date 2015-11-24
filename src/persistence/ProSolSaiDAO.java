package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProdutoSolicitacaoSaida;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class ProSolSaiDAO {
	private Connection c;
	
	public ProSolSaiDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(ProdutoSolicitacaoSaida e) throws EstoqueException {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Saida (quantidade, uso, "
					+ "codProduto, codSaida, codSolicitacao) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdSaida());
			ps.setInt(5, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(ProdutoSolicitacaoSaida e) throws EstoqueException {
		try {
			String sql = "UPDATE produto_Solicitacao_Saida SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "codSaida = ?, codSolicitacao = ? WHERE codSolicitacaoSaida = ?";
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
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM produto_Solicitacao_Saida WHERE codSolicitacaoSaida = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException  {
		String sql = "SELECT MAX(codSolicitacaoSaida) + 1 AS proximo_id FROM produto_Solicitacao_Saida";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return rs.getInt("proximo_id");
			} else {
				return 1;
			}
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}	
	private String getSql(ProdutoSolicitacaoSaida d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codSolicitacaoSaida, quantidade , uso , codSaida , "
					+ "codProduto, codSolicitacao  FROM produto_Solicitacao_Saida ");
		if(d.getCodSolicitacaoSaida() != 0 ){
			sql.append("WHERE codSolicitacaoSaida LIKE '%" +d.getCodSolicitacaoSaida()+"%' ");
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
				sql.append("AND codProduto LIKE '%"+d.getIdProduto()+"%' ");
			}
			else{
				sql.append("WHERE codProduto LIKE '%"+d.getIdProduto()+"%' ");	
				ver++;
			}
		}
		if(d.getIdSaida() != 0){
			if(ver>0){
				sql.append("AND codSaida LIKE '%"+d.getIdSaida()+"%' ");
			}
			else{
				sql.append("WHERE codSaida LIKE '%"+d.getIdSaida()+"%' ");	
				ver++;
			}
		}
		if(d.getIdSolicitacao() != 0){
			if(ver>0){
				sql.append("AND codSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");
			}
			else{
				sql.append("WHERE codSolicitacao LIKE '%"+d.getIdSolicitacao()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<ProdutoSolicitacaoSaida> cons(ProdutoSolicitacaoSaida d) throws EstoqueException {
		List<ProdutoSolicitacaoSaida> lista = new ArrayList<ProdutoSolicitacaoSaida>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
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
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}	
}
