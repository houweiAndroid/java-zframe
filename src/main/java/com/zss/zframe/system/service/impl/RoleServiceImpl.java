package com.zss.zframe.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zss.zframe.system.bean.Role;
import com.zss.zframe.system.mapper.RoleMapper;
import com.zss.zframe.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	public RoleMapper roleMapper;
	
	@Override
	public PageInfo<Role> selectPageRoles(int page_index, int page_size, HashMap<String, Object> map) {
		 int count = roleMapper.selectRoleCount(map);
		 PageHelper.startPage(page_index, page_size, false);
		 PageInfo<Role> info = new PageInfo<Role>(roleMapper.selectAllRoles(map));
		 info.setTotal(count);
		 return info;
	}
	
	@Override
	public List<Role> selectAllRoles(HashMap<String, Object> map) {
		return roleMapper.selectAllRoles(map);
	}

	@Override
	public Role selectRoleById(String role_id) {
		return roleMapper.selectRoleById(role_id);
	}
	
	@Override
	public HashMap<String, Object> insertRole(HashMap<String, Object> map) {
		roleMapper.insertRole(map);
		Long roleId = (Long) map.get("role_id");
		if(roleId > 0){
			String menuIds = map.get("menu_ids").toString();
			String menuArr[] = menuIds.split(",");
			for(String item: menuArr){
				HashMap<String, Object> menuMap = new HashMap<>();
				menuMap.put("role_id", roleId);
				menuMap.put("menu_id", item);
				roleMapper.insertRoleMenu(menuMap);
			}
		}
		return map;
	}

	@Override
	public int updateRole(HashMap<String, Object> map) {
		int cnt = roleMapper.updateRole(map);
		String roleId = (String) map.get("role_id");
		roleMapper.deleteRoleMenu(roleId);
		String menuIds = map.get("menu_ids").toString();
		String menuArr[] = menuIds.split(",");
		for(String item: menuArr){
			HashMap<String, Object> menuMap = new HashMap<>();
			menuMap.put("role_id", roleId);
			menuMap.put("menu_id", item);
			roleMapper.insertRoleMenu(menuMap);
		}
		return cnt;
	}

	@Override
	public int deleteRole(String role_id) {
		return roleMapper.deleteRole(role_id);
	}
	
}
