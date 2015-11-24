package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tipo;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class TipoDAO {
	private Connection c;
	public TipoDAO()  throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(Tipo e) throws EstoqueException {
		try {
			String sql = "INSERT INTO tipo (nome) VALUES (?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(Tipo e) throws EstoqueException {
		try {
			String sql = "UPDATE tipo SET nome = ? WHERE codTipo = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setInt(2, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM tipo WHERE codTipo = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(tipo) + 1 AS proximo_id FROM tipo";
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
	public List<Tipo> cons(Tipo d) throws EstoqueException {
		List<Tipo> lista = new ArrayList<Tipo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codTipo, nome FROM tipo ");
		int ver = 0;
		if(d.getId() != 0){
			sql.append("WHERE codTipo LIKE '%"+d.getId()+"%' ");
			ver++;
		}
		if(d.getNome() != null){
			if(ver>0){
				sql.append("AND nome LIKE '%"+d.getNome()+"%' ");
			}
			else{
				sql.append( "WHERE nome LIKE '%"+d.getNome()+"%' ");
				ver++;
			}
		}
		
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tipo dp = new Tipo();
				dp.setId(rs.getInt("codTipo"));
				dp.setNome(rs.getString("nome"));
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