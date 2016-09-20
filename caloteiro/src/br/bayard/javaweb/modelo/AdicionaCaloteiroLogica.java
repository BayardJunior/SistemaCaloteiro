package br.bayard.javaweb.modelo;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.bayard.javaweb.dao.CaloteiroDAO;
import br.bayard.javaweb.servlets.CaloteiroServletException;

public class AdicionaCaloteiroLogica implements Logica {
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String devendo = request.getParameter("devendo");
		String data = request.getParameter("dataDivida");

		Calendar dataDivida = null;

		try {
			Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			dataDivida = Calendar.getInstance();
			dataDivida.setTime(dataFormatada);
		} catch (ParseException e) {
			throw new CaloteiroServletException();
		}

		Caloteiro c = new Caloteiro();
		c.setNome(nome);
		c.setEmail(email);
		c.setDevendo(new Integer(devendo));
		c.setDataDivida(dataDivida);

		Connection conn = (Connection) request.getAttribute("conn");
		
		CaloteiroDAO dao = new CaloteiroDAO(conn);

		dao.adciona(c);

		RequestDispatcher rd = request
				.getRequestDispatcher("/caloteiro-adicionado.jsp");
		rd.forward(request, response);
	}
}
