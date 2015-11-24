package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Responsavel;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class ResponsavelDAO {
	private Connection c;
	
	public ResponsavelDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(Responsavel e) throws EstoqueException {
		try {
			String sql = "INSERT INTO responsavel (nome, telefone) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);			
		}
	}
	
	public void atualizar(Responsavel e) throws EstoqueException {
		try {
			String sql = "UPDATE responsavel " + 
				     " SET nome = ?, telefone = ? WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remover(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM responsavel WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(codResponsavel) + 1 AS proximo_id FROM responsavel";
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
	private String getSql(Responsavel d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codResponsavel ,nome, telefone FROM responsavel ");
		if(d.getId() != 0 ){
			sql.append("WHERE codResponsavel LIKE '%" +d.getId()+"%' ");
			ver++;
		}		
		if(d.getNome() != null){
			if(ver>0){
				sql.append("AND nome LIKE '%"+d.getNome()+"%' ");
			}
			else{
				sql.append("WHERE nome LIKE '%"+d.getNome()+"%' ");	
				ver++;
			}
		}
		if(d.getTel() != null){
			if(ver>0){
				sql.append("AND telefone LIKE '%"+d.getTel()+"%' ");
			}
			else{
				sql.append("WHERE telefone LIKE '%"+d.getTel()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<Responsavel> cons(Responsavel d) throws EstoqueException  {
		List<Responsavel> lista = new ArrayList<Responsavel>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Responsavel dp = new Responsavel();
				dp.setId(rs.getInt("codResponsavel"));
				dp.setNome	(rs.getString("nome"));
				dp.setTel(rs.getString("telefone"));
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