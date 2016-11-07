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

import br.com.sematec.carrinho.dao.CarrinhoDAO;
import br.com.sematec.carrinho.dao.ProdutoDAO;
import br.com.sematec.carrinho.modelo.Produto;
import br.com.sematec.carrinho.modelo.Usuario;

@WebServlet(urlPatterns = "/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdutoDAO dao;
	private final Logger LOGGER = Logger.getLogger(ProdutoServlet.class.getName());

	public ProdutoServlet() {
		this.dao = new ProdutoDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "lista":
			lista(req, resp);
			break;
		case "carrinho":
			carrinho(req, resp);
			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}

	public List<Produto> lista() {
		return dao.lista();
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("produtoList", dao.lista());
		Usuario u = (Usuario)req.getSession().getAttribute("usuarioLogado"); 
		req.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(u).getTotal());
		req.setAttribute("carrinho",  CarrinhoDAO.getCarrinhos().get(u));
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}
	
	private void carrinho(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario u = (Usuario)req.getSession().getAttribute("usuarioLogado"); 
		req.setAttribute("carrinho",  CarrinhoDAO.getCarrinhos().get(u));
		req.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(u).getTotal());
		String pagina = "/WEB-INF/jsp/carrinho/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	
}
