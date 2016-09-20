package br.bayard.javaweb.filtro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.bayard.javaweb.dao.ConnectionFactory;
import br.bayard.javaweb.servlets.CaloteiroServletException;

@WebFilter (filterName="FiltroConexao", urlPatterns="/sistema")
public class FiltroConexao implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resonse,
			FilterChain jack) throws IOException, ServletException {
		
		Connection conexao = new ConnectionFactory().getConnetion();
		request.setAttribute("conn", conexao);
		jack.doFilter(request, resonse);
		try {
			conexao.close();
		} catch (SQLException e) {
			throw new CaloteiroServletException("Erro ao tentar "
					+ "fechar a conexão com o banco!");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
