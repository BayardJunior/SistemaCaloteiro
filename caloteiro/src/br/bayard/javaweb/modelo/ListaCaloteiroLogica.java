package br.bayard.javaweb.modelo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.bayard.javaweb.dao.CaloteiroDAO;

public class ListaCaloteiroLogica implements Logica{

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Connection conn = (Connection) request.getAttribute("conn");
			
		CaloteiroDAO dao = new CaloteiroDAO(conn);
		List<Caloteiro> lista = new ArrayList<Caloteiro>();
		
		lista = dao.getLista();
		
		request.setAttribute("lista", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaCaloteiros.jsp");
		rd.forward(request, response);
	}

}
