package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Entrada;

public class EntradaDAO {
private Connection c;
	
	public EntradaDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Entrada e) {
		try {
			String sql = "INSERT INTO entrada (codEntrada, data, tipoTransferencia, NFE, "
					+ "dataEmissaoNFE, tempo, codFornecedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			java.sql.Date sd2 = new java.sql.Date( e.getDataEmissarNFE().getTime());
			ps.setInt(1, e.getIdEntrada());
			ps.setDate(2, sd);
			ps.setString(3, e.getTipoTransf());
			ps.setString(4, e.getNFE());
			ps.setDate(5, sd2);
			ps.setFloat(6, e.getTempo());
			ps.setInt(7, e.getIdFornecedor());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, Entrada e) {
		try {
			String sql = "UPDATE entrada " + 
				     " SET data = ?, tipoTransferencia = ?, NFE = ?, dataEmissaoNFE = ?"
				     + ", tempo = ?, codFornecedor = ? WHERE codEntrada = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			java.sql.Date sd2 = new java.sql.Date( e.getDataEmissarNFE().getTime());
			ps.setDate(1, sd);
			ps.setString(2, e.getTipoTransf());
			ps.setString(3, e.getNFE());
			ps.setDate(4, sd2);
			ps.setFloat(5, e.getTempo());
			ps.setInt(6, e.getIdFornecedor());
			ps.setInt(7, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM entrada WHERE codEntrada = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
