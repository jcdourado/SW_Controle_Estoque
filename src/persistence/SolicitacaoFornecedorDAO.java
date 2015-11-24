package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoFornecedor;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class SolicitacaoFornecedorDAO {
	private Connection c;
	public SolicitacaoFornecedorDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(SolicitacaoFornecedor e) throws EstoqueException {
		try {
			String sql = "INSERT INTO solicitacao_Fornecedor (codFornecedor, data) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdFornecedor());
			ps.setDate(2, sd);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar( SolicitacaoFornecedor e) throws EstoqueException {
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
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM solicitacao_Fornecedor WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(codSolicitacao) + 1 AS proximo_id FROM solicitacao_Fornecedor";
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
	private String getSql(SolicitacaoFornecedor d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codSolicitacao ,data, codFornecedor FROM solicitacao_Fornecedor ");
		if(d.getId() != 0 ){
			sql.append("WHERE codSolicitacao LIKE '%" +d.getId()+"%' ");
			ver++;
		}		
		if(d.getIdFornecedor() != 0){
			if(ver>0){
				sql.append("AND codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ");
			}
			else{
				sql.append("WHERE codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<SolicitacaoFornecedor> cons(SolicitacaoFornecedor d) throws EstoqueException{
		List<SolicitacaoFornecedor> lista = new ArrayList<SolicitacaoFornecedor>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
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
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
}