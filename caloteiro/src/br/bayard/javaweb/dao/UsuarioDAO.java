package br.bayard.javaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.bayard.javaweb.modelo.Usuario;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection connection){
		this.conn = connection;
	}
	
	public Usuario autentica(String login, String senha){
		
		String SQL = "select *from usuario where login=? and senha=?";
		
		try {
			PreparedStatement pstm = this.conn.prepareStatement(SQL);
			pstm.setString(1, login);
			pstm.setString(2, senha);
			
			Usuario usuario = null;
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				Long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String uLogin = rs.getString("login");
				String uSenha = rs.getString("senha");
				
				usuario = new Usuario();
				
				usuario.setId(id);
				usuario.setNome(nome);
				usuario.setLogin(uLogin);
				usuario.setSenha(uSenha);
			}
			rs.close();
			pstm.close();
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
}
