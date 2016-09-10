package br.bayard.javaweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.bayard.javaweb.modelo.Caloteiro;

public class CaloteiroDAO {

	private Connection conn = null;

	public CaloteiroDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void closeConn() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionaCaloteiro(Caloteiro c) {
		String SQL = "insert into caloteiro (nome,email,devendo,dataDivida)" + "values(?,?,?,?)";

		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			pstm.setString(1, c.getNome());
			pstm.setString(2, c.getEmail());
			pstm.setInt(3, c.getDevendo());
			pstm.setDate(4, new Date(c.getDataDivida().getTimeInMillis()));

			pstm.execute();
			pstm.close();
			closeConn();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void deletaCaloteiro(Caloteiro c) {
		String SQL = "delete *from caloteiro where id=?";

		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			pstm.setInt(1, c.getId().intValue());
			pstm.execute();
			closeConn();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void alteraCaloteiro(Caloteiro c) {

		String SQL = "update caloteiro set name=?, email=?, devendo=?, dataDivida=?" + "where id=?";

		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			pstm.setString(1, c.getNome());
			pstm.setString(2, c.getEmail());
			pstm.setInt(3, c.getDevendo());
			pstm.setDate(4, new Date(c.getDataDivida().getTimeInMillis()));
			pstm.setInt(5, c.getId().intValue());

			pstm.execute();
			pstm.close();
			closeConn();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Caloteiro> getList() {
		String SQL = "select *from caloteiro";
		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			List<Caloteiro> caloteiros = new ArrayList<Caloteiro>();
			Caloteiro caloteiro = null;
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				caloteiro = new Caloteiro();
				caloteiro.setNome(rs.getString("nome"));
				caloteiro.setEmail(rs.getString("email"));
				caloteiro.setDevendo(rs.getInt("devendo"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataDivida"));
				caloteiro.setDataDivida(data);
				caloteiros.add(caloteiro);
			}
			rs.close();
			pstm.close();
			closeConn();
			return caloteiros;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Caloteiro getCaloteiroById(Long id){
		
		String SQL = "select *from caloteiro where id=?";
		
		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			pstm.setInt(1, id.intValue());
			ResultSet rs = pstm.executeQuery();
			Caloteiro caloteiro=null;
			
			while (rs.next()) {
				Long idCaloteiro = rs.getLong("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int devendo = rs.getInt("devendo");
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataDivida"));
				
				caloteiro = new Caloteiro();
				
				caloteiro.setId(idCaloteiro);
				caloteiro.setNome(nome);
				caloteiro.setEmail(email);
				caloteiro.setDevendo(devendo);
				caloteiro.setDataDivida(data);
			}	
			rs.close();
			pstm.close();
			closeConn();
			return caloteiro;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	
	}
}
