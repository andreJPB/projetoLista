package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.Marshaller.Listener;

import com.dao.DAO;
import com.modelo.Item;
import com.modelo.Lista;

@ManagedBean
@ViewScoped
public class ListaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lista lista = new Lista();
	private Long idItem;
	private List<Item> lidos = new ArrayList<Item>();
	

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public Lista getLista() {
		return lista;
	}
	
	public List<Item> getListaItens() {
		this.lidos = new DAO<Item>(Item.class).listaTodos();
		return lidos;
	}
	
	public List<Item> getListaItensGravados() {
		return this.lista.getItens();
	}

	
	public double getTotal() {
		double total = 0;
		for (Item item : getListaItensGravados()) {
			total += item.getPreco();
		}
		return total;
	}

	public void gravaItem() {
		Item item = new DAO<Item>(Item.class).buscaPorId(getIdItem());
		this.lista.adicionaItem(item);
	}

	public void gravaLista() {
		new DAO<Lista>(Lista.class).adiciona(this.lista);
		this.lista = new Lista();
	}

	public List<Lista> getListas() {
		return new DAO<Lista>(Lista.class).listaTodos();
	}

}
