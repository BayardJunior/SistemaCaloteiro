package br.bayard.javaweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Contador")
public class ServletContador extends HttpServlet {

	private int contador = 0;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

		contador++;

		PrintWriter pw = arg1.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<body>");
		pw.println("Servlet de numero: " + contador);
		pw.println("</body>");
		pw.println("</head>");
		pw.println("</html>");
	}
}
