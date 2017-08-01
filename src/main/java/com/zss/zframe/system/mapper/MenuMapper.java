package com.zss.zframe.system.mapper;

import java.util.HashMap;
import java.util.List;

import com.zss.zframe.system.bean.Menu;

public interface MenuMapper {

	public List<Menu> selectAllMenus(HashMap<String, Object> map);
	
	public List<Menu> selectTreeMenus(HashMap<String, Object> map);
	
	public List<Menu> selectListMenuByPId(String parent_id);
	
	public Menu selectMenuById(String menu_id);
	
	public int insertMenu(HashMap<String, Object> map);
	
	public int updateMenu(HashMap<String, Object> map);
	
	public int deleteMenu(String menu_id);
	
}
