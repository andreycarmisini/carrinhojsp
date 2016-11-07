package br.com.sematec.carrinho.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sematec.carrinho.modelo.Usuario;

public class UsuarioDAO {
	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {
		USUARIOS.put("professor@sematecsolucoes.com.br", new Usuario(1L,"professor", "professor@sematecsolucoes.com.br", "professor"));
		USUARIOS.put("diretor@sematecsolucoes.com.br", new Usuario(2L,"diretor", "diretor@sematecsolucoes.com.br", "diretor"));
	}

	public Usuario buscaPorEmailESenha(String email, String senha) {
		if (!USUARIOS.containsKey(email)) {
			return null;
		}
		Usuario usuario = USUARIOS.get(email);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}

	
	private static void geraIdEAdiciona(Usuario p) {
		
		Long id = Long.valueOf(USUARIOS.size() + 1);
		p.setId(id);
		USUARIOS.put(p.getLogin(),p);
	}
	
	public void adiciona(Usuario p) {
		geraIdEAdiciona(p);
	}
	
	public List<Usuario> lista() {
		return new ArrayList<Usuario>(USUARIOS.values());
	}
	
	public Usuario buscaUsuarioPorLoginESenha(Usuario usuario) {
		if (!USUARIOS.containsKey(usuario.getLogin())) {
			return null;
		}
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		usuario = USUARIOS.get(login);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}
}
