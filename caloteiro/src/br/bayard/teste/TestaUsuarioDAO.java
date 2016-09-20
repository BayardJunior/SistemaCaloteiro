package br.bayard.teste;

import java.sql.Connection;

import br.bayard.javaweb.dao.ConnectionFactory;
import br.bayard.javaweb.dao.UsuarioDAO;
import br.bayard.javaweb.modelo.Usuario;

public class TestaUsuarioDAO {

	public static void main(String[] args) {
		Connection conexao = new ConnectionFactory().getConnetion();
		UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
		
		Usuario usuarioLogado = usuarioDAO.autentica("teste", "teste");
		if (usuarioLogado !=null) {
			System.out.println("Usuario logado: "+usuarioLogado.getNome());
		}
		
		Usuario usuarioErrado = usuarioDAO.autentica("NaoExiste", "Nao");
		if (usuarioErrado==null) {
			System.out.println("Usuario nao Existe");
		}
	}
}
