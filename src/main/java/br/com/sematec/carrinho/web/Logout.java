package br.com.sematec.carrinho.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {

	private final static long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * logout cookie Cookie[] cookies = req.getCookies(); for (Cookie cookie
		 * : cookies) { if (cookie.getName().equals("usuario.logado")) {
		 * cookie.setMaxAge(0); resp.addCookie(cookie); PrintWriter writer =
		 * resp.getWriter();
		 * writer.println("<html><body>Logout efetuado</body></html>"); } }
		 */

		
		HttpSession session = req.getSession();
		session.removeAttribute("usuario.logado");
		session.invalidate();
		String pagina = "/index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);

	}
}
