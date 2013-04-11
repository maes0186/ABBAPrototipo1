package org.beanding.menu;

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
import org.xml.sax.SAXException;
/**
 * Clase encargada de la logica
 * del Menu Dinamico
 * @author Mario
 *
 */
public class MenuBeans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6068767621712393306L;
	/**
	 * 
	 */

	private UIPanelMenu menupanel = null;
	private UIPanelMenuGroup menuGroup;
 /**
  * 
  * @return
  * @throws MalformedURLException
  * @throws SAXException
  * @throws IOException
  */
	public UIPanelMenu getMenu() throws MalformedURLException, SAXException,
			IOException {
		if (menupanel == null) {
			menupanel = new UIPanelMenu();
			initMenu();
		}

		return this.menupanel;
	}
/**
 * 
 * @param obj
 */
	public void setMenu(UIPanelMenu obj) {
	}
/**
 * 
 * @throws MalformedURLException
 * @throws SAXException
 * @throws IOException
 */
	public void initMenu() throws MalformedURLException, SAXException,
			IOException {

		this.menupanel.getChildren().clear();
		this.menupanel.getChildren().addAll(buildMenu());
	}
/**
 * 
 * @param name
 * @param value
 * @param id
 * @return
 */
	public UIPanelMenuItem createItem(String name, String value, String id) {
		ExpressionFactory expFactory = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory();
		ELContext elcontext = FacesContext.getCurrentInstance().getELContext();
		UIPanelMenuItem item = new UIPanelMenuItem();
		item.setId(id);
		item.setLabel(name);
		MethodExpression methodExpression = expFactory.createMethodExpression(
				elcontext, value, String.class, new Class[] {});

		item.setActionExpression(methodExpression);
		return item;

	}
/**
 * 
 * @return
 * @throws MalformedURLException
 * @throws SAXException
 * @throws IOException
 */
	public List<UIPanelMenuGroup> buildMenu() throws MalformedURLException,
			SAXException, IOException {
		Nodo nodo = MenuUtil.getXMLMapeado();
		List<UIPanelMenuGroup> menuGroups = null;
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
/**
 * 
 * @param nodo
 * @param menuGroup
 * @return
 */
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
							createItem(nodoAux.getName(), nodoAux.getValue(),
									nodoAux.getId()));
				menuGroup.setLabel(nodo.getName());
				menuGroup.setId(nodo.getId());
				if (nodo.getTipo().equalsIgnoreCase("config"))
					menuGroup.setId("IdPrincipal");
				else
					menuGroup.setId(nodo.getId());
				recorre(nodoAux, menuGroupAux);
			}
		}
		return 0;
	}

}
