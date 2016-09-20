package br.bayard.javaweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.bayard.javaweb.dao.CaloteiroDAO;
import br.bayard.javaweb.modelo.AdicionaCaloteiroLogica;
import br.bayard.javaweb.modelo.Caloteiro;
import br.bayard.javaweb.modelo.Logica;

/**
 * Servlet implementation class ServletSistema
 */
@WebServlet("/sistema")
public class ServletSistema extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("logica");
		String nomeClasse = "br.triadworks.javaweb.modelo." + acao + "Logica";

		try {
			Class classe = Class.forName(nomeClasse);
			Object obj = classe.newInstance();
			Logica logica = (Logica) obj;
			logica.executa(request, response);
		} catch (ClassNotFoundException e) {
			throw new CaloteiroServletException(e.getMessage());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new CaloteiroServletException(e.getMessage());
		} catch (Exception e) {
			throw new CaloteiroServletException(e.getMessage());
		}

		/*
		 * if (acao.equals("AdicionaCaloteiro")) { try { new
		 * AdicionaCaloteiroLogica().executa(request, response); } catch
		 * (Exception e) { e.printStackTrace(); } }else if
		 * (acao.equals("ListaCaloteiro")) { RequestDispatcher rd =
		 * request.getRequestDispatcher("/listaCaloteiros.jsp");
		 * rd.forward(request, response); }else if
		 * (acao.equals("DeletaCaloteiro")) {
		 * 
		 * String id = request.getParameter("id"); Long idExcluir =
		 * Long.parseLong(id); CaloteiroDAO dao = new CaloteiroDAO();
		 * 
		 * dao.deleta(idExcluir);
		 * 
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/listaCaloteiros.jsp");
		 * rd.forward(request, response); } else if
		 * (acao.equals("AlteraCaloteiro")) {
		 * 
		 * String id = request.getParameter("id"); long idAltera =
		 * Long.parseLong(id); CaloteiroDAO dao = new CaloteiroDAO();
		 * 
		 * Caloteiro c = new Caloteiro();
		 * 
		 * c = dao.getCaloteiro(idAltera);
		 * 
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/carrega-caloteiro.jsp");
		 * rd.forward(request, response);
		 * 
		 * dao.altera(c);
		 * 
		 * RequestDispatcher rdr =
		 * request.getRequestDispatcher("/altera-caloteiro.jsp");
		 * rd.forward(request, response); }
		 */
	}

}
