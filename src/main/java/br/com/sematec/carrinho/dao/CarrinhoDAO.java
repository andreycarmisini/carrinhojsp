package br.com.sematec.carrinho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sematec.carrinho.modelo.Carrinho;
import br.com.sematec.carrinho.modelo.Item;
import br.com.sematec.carrinho.modelo.Usuario;

public class CarrinhoDAO {

	private final static Map<Usuario, Carrinho> CARRINHOS = new HashMap<Usuario, Carrinho>();

	/**
	 * Novo carrinho
	 * 
	 * @param Usuario
	 *            usuario logado
	 * @param List<Item>
	 *            Lista dos itens do carrinho
	 */
	public static void adiciona(Usuario u, List<Item> i) {
		Carrinho c = CARRINHOS.get(u);
		if (c == null) {
			c = new Carrinho();
		}
		if (c.getItens().isEmpty()) {
			c.getItens().addAll(i);
		} else {
			for (Item it : i) {
				boolean flag = false;
				for (Item item : c.getItens()) {
					if (item.getId().equals(it.getId())) {
						flag = true;
						c.getItens().remove(item);
						c.getItens().add(it);
						break;
					}
				}
				if (!flag) {
					c.getItens().add(it);
				}
			}
		}
	}

	public static void novoCarrinho(Usuario u) {
		Carrinho c = CARRINHOS.get(u);
		if (c == null) {
			CARRINHOS.put(u, new Carrinho());
		}

	}

	public static void remove(Usuario u, Item p) {
		Carrinho c = CARRINHOS.get(u);
		c.getItens().remove(p);
	}

	public static Map<Usuario, Carrinho> getCarrinhos() {
		return CARRINHOS;
	}

}