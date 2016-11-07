package br.com.sematec.carrinho.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<Item> itens;
	private BigDecimal total;
	
	public Carrinho() {
		itens= new ArrayList<Item>();
		total=new BigDecimal(0);
	}

	public Carrinho(List<Item> itens, BigDecimal total) {
		super();
		this.itens = itens;
		this.total = total;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public BigDecimal getTotal() {
		total = new BigDecimal(0);
		for (Item i : itens) {
			total = total.add(i.getTotal());
		}
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	
	
	
}
