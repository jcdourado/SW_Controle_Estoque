package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Departamento;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class DepartamentoDAO {
	private Connection c;
	
	public DepartamentoDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(Departamento e) throws EstoqueException {
		try {
			String sql = "INSERT INTO departamento (nome, andar, predio, "
					+ "telefone, codResponsavel) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getAndar());
			ps.setString(3, e.getPredio());
			ps.setString(4, e.getTelefone());
			ps.setInt(5, e.getCodResponsavel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(Departamento e) throws EstoqueException {
		try {
			String sql = "UPDATE departamento SET nome = ?, andar = ?, predio = ?, telefone = ?, "
					+ "codResponsavel = ? WHERE codDepartamento = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getAndar());
			ps.setString(3, e.getPredio());
			ps.setString(4, e.getTelefone());
			ps.setInt(5, e.getCodResponsavel());
			ps.setInt(6, e.getId());
			ps.execute();
			ps.close();
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM departamento WHERE codDepartamento = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	private String getSql(Departamento d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codDepartamento, nome, andar, predio, telefone,"
				+ " codResponsavel FROM departamento ");
		if(d.getId() != 0 ){
			sql.append("WHERE codDepartamento LIKE '%" +d.getId()+"%' ");
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
		if(d.getAndar() != null){
			if(ver>0){
				sql.append("AND andar LIKE '%"+d.getAndar()+"%' ");
			}
			else{
				sql.append("WHERE andar LIKE '%"+d.getAndar()+"%' ");	
				ver++;
			}
		}
		if(d.getPredio() != null){
			if(ver>0){
				sql.append("AND predio LIKE '%"+d.getPredio()+"%' ");
			}
			else{
				sql.append("WHERE predio LIKE '%"+d.getPredio()+"%' ");	
				ver++;
			}
		}
		if(d.getTelefone() != null){
			if(ver>0){
				sql.append("AND telefone LIKE '%"+d.getTelefone()+"%' ");
			}
			else{
				sql.append("WHERE telefone LIKE '%"+d.getTelefone()+"%' ");	
				ver++;
			}
		}
		if(d.getCodResponsavel() != 0){
			if(ver>0){
				sql.append("AND codResponsavel LIKE '%"+d.getCodResponsavel()+"%' ");
			}
			else{
				sql.append("WHERE codResponsavel LIKE '%"+d.getCodResponsavel()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
		public List<Departamento> cons(Departamento d) throws EstoqueException {
		List<Departamento> lista = new ArrayList<Departamento>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Departamento dp = new Departamento();
				dp.setId(rs.getInt("codDepartamento"));
				dp.setNome(rs.getString("nome"));
				dp.setAndar(rs.getString("andar"));
				dp.setPredio(rs.getString("predio"));
				dp.setTelefone(rs.getString("telefone"));
				dp.setCodResponsavel(rs.getInt("codResponsavel"));
				lista.add(dp);
			}
			rs.close();
			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(codDepartamento) + 1 AS proximo_id FROM departamento";
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
}
