package com.conexia.saludcoop.security.dto;

import java.util.List;
import java.util.Vector;

import com.conexia.saludcoop.common.entity.security.MenuItem;
import com.conexia.saludcoop.common.util.I18NUtils;

public class MenuNode {
	private Integer idMenu = 0;
	private String titulo = "";
	private String icon = "";
	private String tooltip = "";
	private String link = "";
	private String permiso = "";
	private Integer parent = null;

	private List<MenuNode> children = new Vector<MenuNode>();

	public MenuNode(MenuItem menuItemEntity) {
		this.idMenu = menuItemEntity.getId();
		this.titulo = I18NUtils.getInstance().getText(
				menuItemEntity.getTitle());
		this.icon = menuItemEntity.getIcon();
		this.tooltip = I18NUtils.getInstance().getText(
				menuItemEntity.getTooltip());
		this.link = menuItemEntity.getLink();

		this.parent = menuItemEntity.getIdParent();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getIdMenu() {
		return idMenu;
	}
	
	

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public boolean equals(Object obj) {
		MenuNode m = (MenuNode) obj;
		return this.idMenu == m.getIdMenu();
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public boolean addChild(MenuNode menuNode) {
		return this.children.add(menuNode);
	}

	public String toHtml() {
		String s = "<li tooltip='" + this.getTooltip() +"'><a href='"+ this.getLink() + "'>" + this.getTitulo() ;
		if (!children.isEmpty()) {
			s = s + "<ul>";
			for (MenuNode node : children) {
				s = s + node.toHtml();
			}
			s= s+ "</ul>";
		}
		s = s + "</li>";
		return s;

	}

}
