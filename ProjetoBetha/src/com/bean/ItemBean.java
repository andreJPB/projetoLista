package com.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dao.DAO;
import com.modelo.Item;

@ManagedBean
@ViewScoped
public class ItemBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Item item = new Item();

	public void grava() {
		new DAO<Item>(Item.class).adiciona(this.item);
		this.item = new Item();
	}

	public List<Item> getListaItens() {
		return new DAO<Item>(Item.class).listaTodos();
	}

	public void remove(Item item) {
		try {
			new DAO<Item>(Item.class).remove(item);
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
