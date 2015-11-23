package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tipo;
import utilities.EstoqueException;

public class RestricaoDAO {
	
	private Connection c;
	public RestricaoDAO() throws EstoqueException {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
		
	public void adicionar(Tipo e, Tipo e2) throws EstoqueException {
		try {
			String sql = "INSERT INTO tipoRestricao (codTipo, codRestTipo_FK) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.setInt(2, e2.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(Tipo e1, Tipo e2) throws EstoqueException {
		try {
			String sql = "DELETE FROM tipoRestricao WHERE codTipo = ? AND codRestTipo_FK = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e1.getId());
			ps.setInt(2, e2.getId());
			ps.execute();
			ps.close();
		} catch (SQLException expection) {
			throw new EstoqueException(expection);
		}
	}

	
	public List<Tipo> cons(Tipo d) throws EstoqueException {
		List<Tipo> lista = new ArrayList<Tipo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codRestTipo_FK, tp.nome, tipo.codTipo FROM tipo"
				+ " INNER JOIN tipoRestricao " + "ON tipo.codTipo = tipoRestricao.codTipo " +
				" INNER JOIN tipo tp " + " ON tp.codTipo = tipoRestricao.codRestTipo_FK ");
		int ver = 0;
		if(d.getId() != 0){
			sql.append("WHERE tipo.codTipo LIKE '%"+d.getId()+"%' ");
			ver++;
		}
		if(d.getNome() != null){
			if(ver>0){
				sql.append("AND tipo.nome LIKE '%"+d.getNome()+"%' ");
			}
			else{
				sql.append( "WHERE tipo.nome LIKE '%"+d.getNome()+"%' ");
				ver++;
			}
		}
		
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Tipo dp = new Tipo();
				dp.setId(rs.getInt("codRestTipo_FK"));
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