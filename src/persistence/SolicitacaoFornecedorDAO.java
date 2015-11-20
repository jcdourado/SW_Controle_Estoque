package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoFornecedor;

public class SolicitacaoFornecedorDAO {
	private Connection c;
	public SolicitacaoFornecedorDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(SolicitacaoFornecedor e) {
		try {
			String sql = "INSERT INTO solicitacao_Fornecedor (codFornecedor, data) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdFornecedor());
			ps.setDate(2, sd);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, SolicitacaoFornecedor e) {
		try {
			String sql = "UPDATE solicitacao_Fornecedor SET codFornecedor = ?, data = ? WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdFornecedor());
			ps.setDate(2, sd);
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM solicitacao_Fornecedor WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codSolicitacao) + 1 AS proximo_id FROM solicitacao_Fornecedor";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}		
	private String getSql(SolicitacaoFornecedor d){
		int ver = 0;
		String sql = "SELECT codSolicitacao ,data, codFornecedor FROM solicitacao_Fornecedor ";
		if(d.getId() != 0 ){
			sql += "WHERE codSolicitacao LIKE '%" +d.getId()+"%' ";
			ver++;
		}		
		if(d.getData() != null){
			java.sql.Date sd = new java.sql.Date( d.getData().getTime() );
			if(ver>0){
				sql += "AND data = '"+sd+"' ";
			}
			else{
				sql += "WHERE data = '%"+sd+"' ";	
				ver++;
			}
		}
		if(d.getIdFornecedor() != 0){
			if(ver>0){
				sql += "AND codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ";
			}
			else{
				sql += "WHERE codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<SolicitacaoFornecedor> cons(SolicitacaoFornecedor d) throws SQLException {
		List<SolicitacaoFornecedor> lista = new ArrayList<SolicitacaoFornecedor>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			SolicitacaoFornecedor dp = new SolicitacaoFornecedor();
			dp.setId(rs.getInt("codSolicitacao"));
			dp.setData(rs.getDate("data"));
			dp.setIdFornecedor(rs.getInt("codFornecedor"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}
}