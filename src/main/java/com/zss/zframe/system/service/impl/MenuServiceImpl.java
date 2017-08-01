package com.zss.zframe.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zss.zframe.system.bean.Menu;
import com.zss.zframe.system.mapper.MenuMapper;
import com.zss.zframe.system.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	public MenuMapper menuMapper;
	
	@Override
	public List<Menu> selectAllMenus(HashMap<String, Object> map) {
		 return menuMapper.selectAllMenus(map);
	}
	
	@Override
	public List<Menu> selectTreeMenus(HashMap<String, Object> map) {
		 return menuMapper.selectTreeMenus(map);
	}

	@Override
	public List<Menu> selectListMenuByPId(String parent_id) {
		return menuMapper.selectListMenuByPId(parent_id);
	}
	
	@Override
	public Menu selectMenuById(String menu_id) {
		return menuMapper.selectMenuById(menu_id);
	}

	@Override
	public HashMap<String, Object> insertMenu(HashMap<String, Object> map) {
		menuMapper.insertMenu(map);
		return map;
	}

	@Override
	public int updateMenu(HashMap<String, Object> map) {
		return menuMapper.updateMenu(map);
	}

	@Override
	public int deleteMenu(String menu_id) {
		return menuMapper.deleteMenu(menu_id);
	}
	
}
