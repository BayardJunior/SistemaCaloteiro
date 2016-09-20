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

	private Connection conexao;
	
	public CaloteiroDAO() {
		// TODO Auto-generated constructor stub
	}

	public CaloteiroDAO(Connection conn) {
		this.conexao = conn;
	}

	public void adciona(Caloteiro c) {
		String sql = "insert into caloteiro (nome, email, devendo, dataDivida)"
				+ "values(?, ?, ?, ?)";

		try {
			PreparedStatement pstm = conexao.prepareStatement(sql);

			pstm.setString(1, c.getNome());
			pstm.setString(2, c.getEmail());
			pstm.setInt(3, c.getDevendo().intValue());
			pstm.setDate(4, new Date(c.getDataDivida().getTimeInMillis()));

			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Caloteiro> getLista() {
		String sql = "select * from caloteiro";
		try {
			PreparedStatement pstm = conexao.prepareStatement(sql);
			List<Caloteiro> caloteiros = new ArrayList<Caloteiro>();
			ResultSet rs = pstm.executeQuery();
			Caloteiro caloteiro = null;
			while (rs.next()) {

				String nome = rs.getString("nome");
				String email = rs.getString("email");
				Integer devendo = rs.getInt("devendo");
				Calendar dataDivida = Calendar.getInstance();
				dataDivida.setTime(rs.getDate("dataDivida"));

				caloteiro = new Caloteiro();

				caloteiro.setNome(nome);
				caloteiro.setEmail(email);
				caloteiro.setDevendo(devendo);
				caloteiro.setDataDivida(dataDivida);

				caloteiros.add(caloteiro);
			}
			rs.close();
			pstm.close();
			return caloteiros;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public void altera(Caloteiro caloteiro) {

		String sql = "update caloteiro set nome=?, email=?"
				+ "devendo=?,dataDivida=?" + "where id=?";

		try {
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setString(1, caloteiro.getNome());
			pstm.setString(2, caloteiro.getEmail());
			pstm.setInt(3, caloteiro.getDevendo());
			pstm.setDate(4, new Date(caloteiro.getDataDivida()
					.getTimeInMillis()));
			pstm.setLong(5, caloteiro.getId());

			pstm.execute();
			pstm.close();
		} catch (SQLException sqle) {
			throw new RuntimeException();
		}

	}

	public void deleta(long id) {

		String sql = "delete from caloteiro where id=?";

		try {
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setLong(1, id);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public Caloteiro getCaloteiro(long id) {
		String sql = "select *from caloteiro where id=?";
		Caloteiro caloteiro = null;
		try {
			PreparedStatement pstm = conexao.prepareStatement(sql);
			pstm.setLong(1, id);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Long idCaloteiro = rs.getLong("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				int devendo = rs.getInt("devendo");

				Calendar dataDivida = Calendar.getInstance();
				dataDivida.setTime(rs.getDate("dataDivida"));

				caloteiro = new Caloteiro();

				caloteiro.setId(idCaloteiro);
				caloteiro.setNome(nome);
				caloteiro.setEmail(email);
				caloteiro.setDevendo(new Integer(devendo));
				caloteiro.setDataDivida(dataDivida);
			}

			rs.close();
			pstm.close();
			return caloteiro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
