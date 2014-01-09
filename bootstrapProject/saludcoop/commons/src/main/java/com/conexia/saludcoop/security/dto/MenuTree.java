package com.conexia.saludcoop.security.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MenuTree {
private List<MenuNode> rootNodes = new Vector<MenuNode>();


private HashMap<Integer, MenuNode> nodos = new HashMap<Integer,MenuNode>();



public boolean add(MenuNode menuNode) {
	if(!this.nodos.containsKey(menuNode.getIdMenu())){
		this.nodos.put(menuNode.getIdMenu(), menuNode);
	}
	if(menuNode.getParent()!=null){
		return this.nodos.get(menuNode.getParent()).addChild(menuNode);
	}else{
		return this.rootNodes.add(menuNode);
	}
	 
}


public boolean contains(Object arg0) {
	
	return nodos.containsValue(((MenuNode)arg0));
}

public List<MenuNode> getRootNodes() {
	
	return rootNodes;
}

public String toHtml(){
	String s = "";
	for (MenuNode node : rootNodes) {
		s = s + node.toHtml();	
	}
	
	return "<ul class='menu'> " + s + "</ul>";
}
}
