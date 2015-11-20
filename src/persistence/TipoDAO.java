package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tipo;

public class TipoDAO {
	private Connection c;
	public TipoDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(Tipo e) {
		try {
			String sql = "INSERT INTO tipo (nome) VALUES (?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(Tipo e) {
		try {
			String sql = "UPDATE tipo SET nome = ? WHERE codTipo = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setInt(2, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM tipo WHERE codTipo = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(tipo) + 1 AS proximo_id FROM tipo";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
	public List<Tipo> cons(Tipo d) throws SQLException {
		List<Tipo> lista = new ArrayList<Tipo>();
		String sql = "SELECT codTipo, nome FROM tipo ";
		int ver = 0;
		if(d.getId() != 0){
			sql += "WHERE codTipo LIKE '%"+d.getId()+"%' ";
			ver++;
		}
		if(d.getNome() != null){
			if(ver>0){
				sql += "AND nome LIKE '%"+d.getNome()+"%' ";
			}
			else{
				sql +=  "WHERE nome LIKE '%"+d.getNome()+"%' ";
				ver++;
			}
		}
		
		PreparedStatement ps = c.prepareStatement(sql);
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
	}
}