package org;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.richfaces.component.UIPanelMenu;
import org.richfaces.component.UIPanelMenuGroup;
import org.richfaces.component.UIPanelMenuItem;
import org.richfaces.component.UIParameter;
import org.util.*;
import org.xml.sax.SAXException;

public class Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nodo allMapping;

	public Nodo getAllMapping() throws MalformedURLException, SAXException, IOException {
		if(allMapping==null)allMapping= getXMLNode();
		return allMapping;
	}

	public void setAllMapping(Nodo allMapping) {
		this.allMapping = allMapping;
	}

	private Nodo getXMLNode() throws MalformedURLException, SAXException, IOException {
		Nodo mapping = new Nodo();
		mapping = MenuUtil.getXMLMapeado();
		return mapping;
	}
	
	private UIPanelMenu menupanel = null;
	 

	 
	public UIPanelMenu getMenu() throws MalformedURLException, SAXException, IOException{
		menupanel= new UIPanelMenu();
		initMenu();
	   return this.menupanel; 
	}
	 
	public void setMenu(UIPanelMenu obj){
	   this.menupanel=obj;
	}
	
	
	
	



	public String getRequestURL() {
		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURL().toString();
		} else {
			return "";
		}
	}
	
	public void initMenu() throws MalformedURLException, SAXException, IOException{ 
        this.menupanel.getChildren().clear();
        this.menupanel.getChildren().addAll(buildMenu()); 
}
	
	public UIPanelMenuItem createItem(String id){
		ExpressionFactory expFactory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        ELContext elcontext = FacesContext.getCurrentInstance().getELContext(); 
        
		 UIPanelMenuItem item = new UIPanelMenuItem();
	        item.setId(id);
	       // item.setLeftIcon(mod.getIconURI());
	        item.setLabel("Label");
	        MethodExpression methodExpression = expFactory.createMethodExpression(elcontext, "commandButton", null, new Class[0]);
	        item.setActionExpression(methodExpression);
	        item.setRender("render1");
	        item.setOncomplete("resize();");
	        //Create Param tag
	        UIParameter param = new UIParameter();
	        param.setName("param1");
	        param.setValue("value1"); 
	        item.getChildren().add(param); 
	        return item;
		
	}
	
	 

public List<UIPanelMenuGroup> buildMenu() throws MalformedURLException, SAXException, IOException{
	    Nodo nodo=MenuUtil.getXMLMapeado();
	    
        List<UIPanelMenuGroup> menuGroups = new ArrayList<UIPanelMenuGroup>();
        UIPanelMenuGroup menuGroup = new UIPanelMenuGroup();
        menuGroup.setLabel("Texto1");
        UIPanelMenuGroup menuGroup2 = new UIPanelMenuGroup();
        menuGroup.getChildren().add(createItem("id1"));
        menuGroup2.getChildren().add(createItem("id2"));
        menuGroup2.setLabel("Texto2");
        menuGroup.getChildren().add(menuGroup2);
        menuGroups.add(menuGroup);
      
       
        return menuGroups; 
}

}
