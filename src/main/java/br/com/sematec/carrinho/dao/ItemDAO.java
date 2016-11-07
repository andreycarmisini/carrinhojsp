package br.com.sematec.carrinho.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sematec.carrinho.modelo.Item;
import br.com.sematec.carrinho.modelo.Usuario;

public class ItemDAO {

	private void geraIdEAdiciona(Usuario u, Item it) {
		List<Item> its = CarrinhoDAO.getCarrinhos().get(u).getItens();
		boolean novoProd = true;
		for (Item i : its) {
			if (i.getProduto().getId().equals(it.getProduto().getId())) {
				novoProd = false;
				i.setQuantidade(i.getQuantidade() + it.getQuantidade());
				i.setTotal(i.getProduto().getPreco().multiply(new BigDecimal(i.getQuantidade())));
			}
		}

		if (novoProd) {
			String id = String.valueOf(ITENS.get(u) == null ? 1 : ITENS.get(u).size() + 1);
			it.setId(id);
			it.setTotal(it.getProduto().getPreco().multiply(new BigDecimal(it.getQuantidade())));

			
			if (ITENS.get(u) == null) {
				Map<String, Item> map = new HashMap<String, Item>();
				map.put(id, it);
				ITENS.put(u, map);
			}else{
				Map<String, Item> map = ITENS.get(u);
				map.put(id, it);
				ITENS.put(u, map);
			}

		}
	}

	private final static Map<Usuario, Map<String, Item>> ITENS = new HashMap<Usuario, Map<String, Item>>();

	public void adiciona(Usuario u, Item i) {
		geraIdEAdiciona(u, i);
	}

	public List<Item> lista(Usuario u) {
		Map<String, Item> map = ITENS.get(u);
		return new ArrayList<Item>(map.values());
	}

	public static void remove(Usuario u, Item p) {
		Map<String, Item> map = ITENS.get(u);
		map.remove(p.getId());
	}
}