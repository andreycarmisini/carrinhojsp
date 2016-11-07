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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
		try {
			HttpSession session = req.getSession(true);
			Usuario temp = (Usuario)session.getAttribute("usuarioLogado");
			if (null == temp || !temp.getLogin().equals((String)req.getParameter("login"))) {
				Usuario usuario = new Usuario();
				usuario.setLogin(req.getParameter("login"));
				usuario.setSenha(req.getParameter("senha"));
				usuario = new UsuarioDAO().buscaUsuarioPorLoginESenha(usuario);
				if (usuario != null) {
					session.setAttribute("usuarioLogado", usuario);
					CarrinhoDAO.novoCarrinho(usuario);//cria o novo carrinho
					req.setAttribute("produtoList", new ProdutoDAO().lista());
					req.setAttribute("carrinhoTotal", CarrinhoDAO.getCarrinhos().get(usuario).getTotal());
					req.setAttribute("carrinho",  CarrinhoDAO.getCarrinhos().get(usuario));
					String pagina = "/WEB-INF/jsp/produto/lista.jsp";
					RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
					dispatcher.forward(req, resp);
				} else {
					req.setAttribute("erro", "email ou senha inválidos.");
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
