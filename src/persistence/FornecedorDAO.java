package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class FornecedorDAO {
	private Connection c;
	
	public FornecedorDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(Fornecedor e) throws EstoqueException {
		try {
			String sql = "INSERT INTO fornecedor (rua, numero, bairro, "
					+ "cidade, estado, nome, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getRua());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getBairro());
			ps.setString(4, e.getCidade());
			ps.setString(5, e.getEstado());
			ps.setString(6, e.getNome());
			ps.setString(7, e.getTel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(Fornecedor e) throws EstoqueException {
		try {
			String sql = "UPDATE fornecedor SET rua = ?, numero = ?, bairro = ?, cidade = ?, "
					+ "estado = ?, nome = ?, telefone = ? WHERE codFornecedor = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getRua());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getBairro());
			ps.setString(4, e.getCidade());
			ps.setString(5, e.getEstado());
			ps.setString(6, e.getNome());
			ps.setString(7, e.getTel());
			ps.setInt(8, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM fornecedor WHERE codFornecedor = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException  {
		String sql = "SELECT MAX(codFornecedor) + 1 AS proximo_id FROM fornecedor";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return rs.getInt("proximo_id");
			} else {
				return 1;
			}
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
		
	}
	
	private String getSql(Fornecedor d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codFornecedor, rua, numero, bairro, cidade , "
					+ "estado, nome , telefone FROM fornecedor ");
		if(d.getId() != 0 ){
			sql.append("WHERE codFornecedor LIKE '%" +d.getId()+"%' ");
			ver++;
		}		
		if(d.getRua() != null){
			if(ver>0){
				sql.append("AND rua LIKE '%"+d.getRua()+"%' ");
			}
			else{
				sql.append("WHERE rua LIKE '%"+d.getRua()+"%' ");	
				ver++;
			}
		}		
		if(d.getNumero() != 0){
			if(ver>0){
				sql.append("AND numero LIKE '%"+d.getNumero()+"%' ");
			}
			else{
				sql.append("WHERE numero LIKE '%"+d.getNumero()+"%' ");	
				ver++;
			}
		}
		if(d.getBairro() != null){
			if(ver>0){
				sql.append("AND bairro LIKE '%"+d.getBairro()+"%' ");
			}
			else{
				sql.append("WHERE bairro LIKE '%"+d.getBairro()+"%' ");	
				ver++;
			}
		}
		if(d.getCidade() != null){
			if(ver>0){
				sql.append("AND cidade LIKE '%"+d.getCidade()+"%' ");
			}
			else{
				sql.append("WHERE cidade LIKE '%"+d.getCidade()+"%' ");	
				ver++;
			}
		}
		if(d.getEstado() != null){
			if(ver>0){
				sql.append("AND estado LIKE '%"+d.getEstado()+"%' ");
			}
			else{
				sql.append("WHERE estado LIKE '%"+d.getEstado()+"%' ");	
				ver++;
			}
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
	
	public List<Fornecedor> cons(Fornecedor d) throws EstoqueException {
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Fornecedor dp = new Fornecedor();
				dp.setId(rs.getInt("codFornecedor"));
				dp.setRua(rs.getString("rua"));
				dp.setNumero(rs.getInt("numero"));
				dp.setBairro(rs.getString("bairro"));
				dp.setCidade(rs.getString("cidade"));
				dp.setEstado(rs.getString("estado"));
				dp.setNome(rs.getString("nome"));
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
