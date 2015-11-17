package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Departamento;

public class DepartamentoDAO {
	private Connection c;
	
	public DepartamentoDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
	
}
