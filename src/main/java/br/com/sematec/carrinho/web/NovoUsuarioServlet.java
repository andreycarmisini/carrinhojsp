package br.com.sematec.carrinho.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sematec.carrinho.dao.CarrinhoDAO;
import br.com.sematec.carrinho.dao.ProdutoDAO;
import br.com.sematec.carrinho.dao.UsuarioDAO;
import br.com.sematec.carrinho.modelo.Usuario;

@WebServlet(urlPatterns = "/novo")
public class NovoUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;

	public NovoUsuarioServlet() {
		this.dao = new UsuarioDAO();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		try {
			HttpSession session = req.getSession(true);
			Usuario temp = (Usuario)session.getAttribute("usuarioLogado");
			if (null == temp || !temp.getLogin().equals((String)req.getParameter("login"))) {
				Usuario usuario = new Usuario();
				usuario.setLogin(req.getParameter("login"));
				usuario.setSenha(req.getParameter("senha"));
				usuario = dao.buscaUsuarioPorLoginESenha(usuario);
				if (usuario == null) {
					Usuario u = new Usuario();
					u.setLogin(req.getParameter("login"));
					u.setSenha(req.getParameter("senha"));
					u.setNome(req.getParameter("nome"));
					dao.adiciona(u);
					session.setAttribute("usuarioLogado", u);
					CarrinhoDAO.novoCarrinho(u);//cria o novo carrinho
					req.setAttribute("produtoList", new ProdutoDAO().lista());
					req.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(u).getTotal());
					req.setAttribute("carrinho",  CarrinhoDAO.getCarrinhos().get(u));
					String pagina = "/WEB-INF/jsp/produto/lista.jsp";
					RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
					dispatcher.forward(req, resp);
				} else {
					req.setAttribute("erro", "usuario já cadastrado.");
					String pagina = "/index.jsp";
					RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
					dispatcher.forward(req, resp);
				}
			}else{
				req.setAttribute("produtoList", new ProdutoDAO().lista());
				req.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get((Usuario)session.getAttribute("usuarioLogado")).getTotal());
				req.setAttribute("carrinho",  CarrinhoDAO.getCarrinhos().get((Usuario)session.getAttribute("usuarioLogado")));
				String pagina = "/WEB-INF/jsp/produto/lista.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
				dispatcher.forward(req, resp);
			}

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
