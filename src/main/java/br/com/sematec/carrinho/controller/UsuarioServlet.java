package br.com.sematec.carrinho.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.carrinho.dao.UsuarioDAO;
import br.com.sematec.carrinho.modelo.Usuario;

@WebServlet(urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
	private final Logger LOGGER = Logger.getLogger(UsuarioServlet.class.getName());

	public UsuarioServlet() {
		this.dao = new UsuarioDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "lista":
			lista(req, resp);
			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}

	public List<Usuario> lista() {
		return dao.lista();
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("usuarioList", dao.lista());
		String pagina = "/WEB-INF/jsp/usuario/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

}
