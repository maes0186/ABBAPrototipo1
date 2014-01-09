package com.conexia.saludcoop.web.manager;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conexia.saludcoop.common.entity.security.MenuItem;
import com.conexia.saludcoop.common.exception.NotExistenItemsDeMenuException;
import com.conexia.saludcoop.common.repository.MenuRepository;
import com.conexia.saludcoop.security.dto.MenuNode;
import com.conexia.saludcoop.security.dto.MenuTree;

@Service
@Transactional
public class MenuManager {

	@Autowired
	private MenuRepository menuRepo;

	public MenuTree getMenuTree(Set<Integer> authIds) throws NotExistenItemsDeMenuException {

		MenuTree mt = new MenuTree();
		;
		
		for (MenuItem me : menuRepo.findByAuthorityIn(authIds)) {
			mt.add(new MenuNode(me));
		}
		return mt;
	}
	
	
}
