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
import javax.servlet.http.HttpSession;

import br.com.sematec.carrinho.dao.CarrinhoDAO;
import br.com.sematec.carrinho.dao.ItemDAO;
import br.com.sematec.carrinho.dao.ProdutoDAO;
import br.com.sematec.carrinho.modelo.Item;
import br.com.sematec.carrinho.modelo.Produto;
import br.com.sematec.carrinho.modelo.Usuario;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDAO dao;
	private final Logger LOGGER = Logger.getLogger(ItemServlet.class.getName());

	public ItemServlet() {
		this.dao = new ItemDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String funcao = req.getParameter("funcao");
		switch (funcao) {
		case "remove":
//			remove(req, resp);
			break;
		case "lista":
			lista(req, resp);
			break;
//		case "add":
//			adiciona(req, resp);
//			break;
		default:
			LOGGER.warning("função desconhecida:" + funcao);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String text;
		if (req.getParameter("id") != null && !req.getParameter("id").isEmpty() && req.getParameter("quantidade") != null && !req.getParameter("quantidade").isEmpty()) {
			Integer quantidade = Integer.valueOf(req.getParameter("quantidade"));
			String id = String.valueOf(req.getParameter("id"));
			Produto p = new ProdutoDAO().find(id);
			Item item = new Item(p,quantidade);
			Usuario u = (Usuario)req.getSession().getAttribute("usuarioLogado"); 
			dao.adiciona(u,item);
			//req.setAttribute("itemList", dao.lista());
			CarrinhoDAO.adiciona(u,dao.lista(u));
			
			HttpSession session = req.getSession(); 
		    session.setAttribute("carrinho", CarrinhoDAO.getCarrinhos().get(u));
		    session.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(u).getTotal());
			//req.setAttribute("carrinho",CarrinhoDAO.getCarrinho().getTotal());	        
		    
			//text = "Item adicionado com sucesso.";
		} else {
			//text = "id do item a ser adicionado ou a sua quantidade não foi informado.";
		}
		req.setAttribute("produtoList", new ProdutoDAO().lista());
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

	public List<Item> lista(Usuario u) {
		return dao.lista(u);
	}

	private void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario u = (Usuario)req.getSession().getAttribute("usuarioLogado"); 
		req.setAttribute("itemList", dao.lista(u));
		String pagina = "/WEB-INF/jsp/item/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
	}

//	private void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String text;
//		if (req.getParameter("id") != null) {
//			String id = String.valueOf(req.getParameter("id"));
//			Item i = dao.find(id);
//			dao.remove(i);
//			text = "Item removido com sucesso.";
//		} else {
//			text = "id do item a ser removido não foi informado.";
//		}
//		resp.setContentType("text/plain");
//		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(text);
//	}
//
//	public void remove(Item item) {
//		dao.remove(item);
//	}
	
	/*private void adiciona(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String text;
		if (req.getParameter("id") != null && !req.getParameter("id").isEmpty() && req.getParameter("quantidade") != null && !req.getParameter("quantidade").isEmpty()) {
			Integer quantidade = Integer.valueOf(req.getParameter("quantidade"));
			String id = String.valueOf(req.getParameter("id"));
			Produto p = new ProdutoDAO().find(id);
			Item item = new Item(p,quantidade);
			dao.adiciona(item);
			//req.setAttribute("itemList", dao.lista());
			
			CarrinhoDAO.adiciona(dao.lista());
			
			HttpSession session = req.getSession(); 
		    session.setAttribute("carrinho", CarrinhoDAO.getCarrinho());
			//req.setAttribute("carrinho",CarrinhoDAO.getCarrinho().getTotal());
		    
		    String redirect = resp.encodeRedirectURL(req.getContextPath() + "/usuario?funcao=lista" );
		    resp.sendRedirect(redirect);
		    
		    
			//text = "Item adicionado com sucesso.";
		} else {
			//text = "id do item a ser adicionado ou a sua quantidade não foi informado.";
		}
		String pagina = "/WEB-INF/jsp/produto/lista.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
		dispatcher.forward(req, resp);
		
		
		//resp.setContentType("text/plain");
		//resp.setCharacterEncoding("UTF-8");
		//resp.getWriter().write(text);
	}*/

	
}
