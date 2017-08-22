package com.zss.zframe.system.service;

import java.util.HashMap;
import java.util.List;

import com.zss.zframe.system.bean.Menu;

/**
 * 系统菜单服务接口
 * @author zm
 *
 */
public interface MenuService {

	public List<Menu> selectAllMenus(HashMap<String, Object> map);
	
	public List<Menu> selectTreeMenus(HashMap<String, Object> map);
	
	public List<Menu> selectListMenuByPId(String menu_id);
	
	public Menu selectMenuById(String menu_id);
	
	public HashMap<String, Object> insertMenu(HashMap<String, Object> map);
	
	public int updateMenu(HashMap<String, Object> map);
	
	public int deleteMenu(String menu_id);
	
	public List<Menu> selectUserMenu(String user_id);
	
}
