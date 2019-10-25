package com.kando.entity;

import java.io.Serializable;

public class Authority implements Serializable {
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String menu_id;

	private Integer fun_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public Integer getFun_id() {
		return fun_id;
	}

	public void setFun_id(Integer fun_id) {
		this.fun_id = fun_id;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", menu_id='" + menu_id + '\'' +
				", fun_id=" + fun_id +
				'}';
	}
}
