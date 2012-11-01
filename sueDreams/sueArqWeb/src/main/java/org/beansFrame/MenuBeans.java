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
import javax.faces.el.MethodBinding;

import org.richfaces.PanelMenuMode;
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
	private static final long serialVersionUID = 6068767621712393306L;
	/**
	 * 
	 */
	
	private UIPanelMenu menupanel = null;
	//private int contId = 1;
	//private int contItem = 1;
	UIPanelMenuGroup menuGroup;
	

	public UIPanelMenu getMenu() throws MalformedURLException, SAXException,
			IOException {
		if(menupanel==null){menupanel = new UIPanelMenu();
		initMenu();
		}
		
		return this.menupanel;
	}

	public void setMenu(UIPanelMenu obj) {
		//this.menupanel = obj;
	}

	public void initMenu() throws MalformedURLException, SAXException,
			IOException {

		this.menupanel.getChildren().clear();
		this.menupanel.getChildren().addAll(buildMenu());
	}

	public UIPanelMenuItem createItem(String name, String value,String id) {
		ExpressionFactory expFactory = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory();
		ELContext elcontext = FacesContext.getCurrentInstance().getELContext();
		UIPanelMenuItem item = new UIPanelMenuItem();
		//String idr =String.valueOf("idTest"+String.valueOf(contId));//+contItem;
		item.setId(id);
		//item.setRender("menuH");
		//item.setMode(PanelMenuMode.ajax);
		item.setLabel(name);
		//item.setImmediate(true);
		MethodExpression methodExpression = expFactory.createMethodExpression(
                elcontext, value, String.class, new Class[]{});
		
		item.setActionExpression(methodExpression);
		return item;

	}

	public List<UIPanelMenuGroup> buildMenu() throws MalformedURLException,
			SAXException, IOException {
		Nodo nodo = MenuUtil.getXMLMapeado();
		List<UIPanelMenuGroup> menuGroups=null;
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
							createItem(nodoAux.getName(), nodoAux.getValue(),nodoAux.getId()));
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

	
	
	public UIPanelMenu getMenuTest(){
		UIPanelMenu panelMenu =null;
		try{
		panelMenu = new UIPanelMenu();
		UIPanelMenuGroup menuGroup = new UIPanelMenuGroup();
		
		//panelMenu.setId("idTest");
		
		menuGroup.setLabel("LabelMenuGroup1");
		
		List<UIPanelMenuGroup> lst = new ArrayList<UIPanelMenuGroup>();
		
		
		//panelMenu.getChildren().add(menuGroup);
		menuGroup.getChildren().add(this.createItem("Label1", "value","idTest1"));
		//lst.add(menuGroup);
		menuGroup=new UIPanelMenuGroup();
		menuGroup.setLabel("LabelMenuGroup2");
		
		menuGroup.getChildren().add(this.createItem("Label2", "value","idTest12"));
		//lst.add(menuGroup);
		lst=this.buildMenu();
		for(UIPanelMenuGroup group:lst){
			group.setParent(null);
		}
		panelMenu.getChildren().addAll(this.buildMenu());
		}
		catch(Exception e){
			return null;
		}
		
		return panelMenu;
	}
	
	public void setMenuTest(UIPanelMenu menuTest){
		
	}
	
}
