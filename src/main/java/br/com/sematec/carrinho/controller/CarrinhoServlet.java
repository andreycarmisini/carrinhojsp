package br.com.sematec.carrinho.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.carrinho.dao.CarrinhoDAO;
import br.com.sematec.carrinho.dao.ItemDAO;
import br.com.sematec.carrinho.modelo.Item;
import br.com.sematec.carrinho.modelo.Usuario;

@WebServlet(urlPatterns = "/carrinho")
public class CarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = Logger.getLogger(CarrinhoServlet.class.getName());

	public CarrinhoServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "remove":
			remove(req, resp);
			break;
		case "lista":
			lista(req, resp);
			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
		req.setAttribute("carrinho", CarrinhoDAO.getCarrinhos().get(u));
		String pagina = "/WEB-INF/jsp/carrinho/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String text;
		if (req.getParameter("id") != null) {
			String id = String.valueOf(req.getParameter("id"));
			Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");
			for (Item i : CarrinhoDAO.getCarrinhos().get(u).getItens()) {
				if (i.getId().equals(id)) {
					CarrinhoDAO.remove(u, i);
					ItemDAO.remove(u, i);
					break;
				}
			}
			req.setAttribute("carrinho", CarrinhoDAO.getCarrinhos().get(u));
			req.getSession().setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(u).getTotal());
			text = "Item removido com sucesso.";
		} else {
			text = "id do item a ser removido não foi informado.";
		}
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(text);
	}

}
