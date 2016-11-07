package br.com.sematec.carrinho.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sematec.carrinho.modelo.Produto;

public class ProdutoDAO {
	private static void geraIdEAdiciona(Produto p) {
		String id = String.valueOf(PRODUTOS.size() + 1);
		p.setId(id);
		PRODUTOS.put(id, p);
	}

	private final static Map<String, Produto> PRODUTOS = new HashMap<>();
	static {

		geraIdEAdiciona(new Produto("1", "caneta", "http://www.culturamix.com/wp-content/uploads/2010/07/342.jpg", new BigDecimal(5)));
		geraIdEAdiciona(new Produto("2", "livro",  "https://thumbs.dreamstime.com/z/livros-do-vetor-20575954.jpg", new BigDecimal(15)));
		geraIdEAdiciona(new Produto("3", "Geladeira",  "http://www.pontofrio-imagens.com.br/Eletrodomesticos/GeladeiraeRefrigerador/FrostFree/46706/5244305/Refrigerador-Brastemp-Frost-Free-Duplex-Clean-BRM39ER-352-L-Inox-46705.jpg", new BigDecimal(1000)));
		geraIdEAdiciona(new Produto("4", "Videogame da Microsoft", "http://www.extra-imagens.com.br/Games/Xbox360/ConsolesXbox360/5573065/190290430/Console-Xbox-360-4GB-2-Controles-Wireless-5573065.jpg",  new BigDecimal(200)));
		geraIdEAdiciona(new Produto("5", "Samsung Novo ", "http://2.bp.blogspot.com/_zgAz60kWH2Q/TRWVPUM252I/AAAAAAAAEaA/AuelRbQ2u4Q/s1600/Samsung-GalaxyS-Main.jpg", new BigDecimal(500)));
	}

	public void adiciona(Produto p) {
		geraIdEAdiciona(p);
	}

	public void atualiza(Produto p) {
		PRODUTOS.put(p.getId(), p);
	}

	public Produto find(String id) {
		return PRODUTOS.get(id);
	}

	public List<Produto> lista() {
		return new ArrayList<Produto>(PRODUTOS.values());
	}

	public void remove(String id) {
		PRODUTOS.remove(id);
	}

	public void remove(Produto p) {
		remove(p.getId());
	}
}