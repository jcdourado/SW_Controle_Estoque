package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoDepartamento;
import utilities.EstoqueException;

public class SolicitacaoDepartamentoDAO {
	private Connection c;
	public SolicitacaoDepartamentoDAO() throws EstoqueException {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(SolicitacaoDepartamento e) throws EstoqueException {
		try {
			String sql = "INSERT INTO solicitacao_Departamento (codDepartamento, data) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdDepartamento());
			ps.setDate(2, sd);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(SolicitacaoDepartamento e) throws EstoqueException {
		try {
			String sql = "UPDATE solicitacao_Departamento SET codDepartamento = ?, data = ? WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdDepartamento());
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
			String sql = "DELETE FROM solicitacao_Departamento WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(codSolicitacao) + 1 AS proximo_id FROM solicitacao_Departamento";
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
	private String getSql(SolicitacaoDepartamento d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codSolicitacao ,data, codDepartamento FROM solicitacao_Departamento ");
		if(d.getId() != 0 ){
			sql.append("WHERE codSolicitacao LIKE '%" +d.getId()+"%' ");
			ver++;
		}		
		if(d.getIdDepartamento() != 0){
			if(ver>0){
				sql.append("AND codDepartamento LIKE '%"+d.getIdDepartamento()+"%' ");
			}
			else{
				sql.append("WHERE codDepartamento LIKE '%"+d.getIdDepartamento()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<SolicitacaoDepartamento> cons(SolicitacaoDepartamento d) throws EstoqueException {
		List<SolicitacaoDepartamento> lista = new ArrayList<SolicitacaoDepartamento>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SolicitacaoDepartamento dp = new SolicitacaoDepartamento();
				dp.setId(rs.getInt("codSolicitacao"));
				dp.setData(rs.getDate("data"));
				dp.setIdDepartamento(rs.getInt("codDepartamento"));
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
