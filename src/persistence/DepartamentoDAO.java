package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Departamento;

public class DepartamentoDAO {
	private Connection c;
	
	public DepartamentoDAO() {
		GenericDAO gen = new GenericDAO();
		c = gen.getConnection();
	}
	
	public void adicionar(Departamento e) {
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
			e1.printStackTrace();
		}
	}
	
	public void atualizar(Departamento e) {
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
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM departamento WHERE codDepartamento = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<Departamento> cons() throws SQLException {
		List<Departamento> lista = new ArrayList<Departamento>();
		String sql = "SELECT codDepartamento, nome, andar, predio, telefone,"
				+ " codResponsavel FROM departamento";
		PreparedStatement ps = c.prepareStatement(sql);
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
	}

	public List<Departamento> consPorId(int id) throws SQLException {
		List<Departamento> lista = new ArrayList<Departamento>();
		String sql = "SELECT codDepartamento, nome, andar, predio, telefone,"
				+ " codResponsavel FROM departamento WHERE codDepartamento LIKE '%?%'";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
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
	}

	public List<Departamento> consPorNome(String nome) throws SQLException {
		List<Departamento> lista = new ArrayList<Departamento>();
		String sql = "SELECT codDepartamento, nome, andar, predio, telefone,"
				+ " codResponsavel FROM departamento WHERE nome LIKE '%?%'";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, nome);
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
	}
	
	public List<Departamento> consPorNome(String nome) throws SQLException {
		List<Departamento> lista = new ArrayList<Departamento>();
		String sql = "SELECT codDepartamento, nome, andar, predio, telefone,"
				+ " codResponsavel FROM departamento WHERE nome LIKE '%?%'";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, nome);
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
	}
	
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codDepartamento) + 1 AS proximo_id FROM departamento";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}	
}
