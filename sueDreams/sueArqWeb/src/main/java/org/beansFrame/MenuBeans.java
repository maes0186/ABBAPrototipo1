package org.beansFrame;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.richfaces.component.UIPanelMenu;
import org.richfaces.component.UIPanelMenuGroup;
import org.richfaces.component.UIPanelMenuItem;
import org.util.MenuUtil;
import org.util.Nodo;
import org.xml.sax.SAXException;

public class MenuBeans implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UIPanelMenu menupanel = null;
	private int contId = 0;
	private Integer contItem = 0;
	UIPanelMenuGroup menuGroup;
	List<UIPanelMenuGroup> menuGroups;

	public UIPanelMenu getMenu() throws MalformedURLException, SAXException,
			IOException {
		menupanel = new UIPanelMenu();
		initMenu();
		return this.menupanel;
	}

	public void setMenu(UIPanelMenu obj) {
		this.menupanel = obj;
	}

	public void initMenu() throws MalformedURLException, SAXException,
			IOException {

		this.menupanel.getChildren().clear();
		this.menupanel.getChildren().addAll(buildMenu());
	}

	public UIPanelMenuItem createItem(String name, String value) {
		ExpressionFactory expFactory = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory();
		ELContext elcontext = FacesContext.getCurrentInstance().getELContext();
		UIPanelMenuItem item = new UIPanelMenuItem();
		String idr = "idItem" + contItem;
		item.setId(idr);
		contItem++;
		item.setLabel(name);
		MethodExpression methodExpression = expFactory.createMethodExpression(
				elcontext, value, null, new Class[0]);
		item.setActionExpression(methodExpression);
		return item;

	}

	public List<UIPanelMenuGroup> buildMenu() throws MalformedURLException,
			SAXException, IOException {
		Nodo nodo = MenuUtil.getXMLMapeado();

		if (menuGroups == null) {
			menuGroup = new UIPanelMenuGroup();
			menuGroups = new ArrayList<UIPanelMenuGroup>();
			this.recorre(nodo, menuGroup);
		}

		for (UIComponent mg : menuGroup.getChildren()) {
			menuGroups.add((UIPanelMenuGroup) mg);
		}

		return menuGroups;
	}

	public int recorre(Nodo nodo, UIPanelMenuGroup menuGroup) {

		if (nodo == null)
			return 0;
		if (!(nodo.getChilds() == null)) {

			for (Nodo nodoAux : nodo.getChilds()) {
				UIPanelMenuGroup menuGroupAux = new UIPanelMenuGroup();
				menuGroupAux.getChildren().clear();
				if (!(nodoAux.getChilds() == null))
					menuGroup.getChildren().add(menuGroupAux);
				else
					menuGroup.getChildren().add(
							createItem(nodoAux.getName(), nodoAux.getValue()));
				menuGroup.setLabel(nodo.getName());
				menuGroup.setId("menu" + contId);
				contId++;
				recorre(nodoAux, menuGroupAux);
			}
		}

		return 0;
	}
}
