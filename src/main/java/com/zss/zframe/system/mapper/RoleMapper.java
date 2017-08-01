package com.zss.zframe.system.mapper;

import java.util.HashMap;
import java.util.List;

import com.zss.zframe.system.bean.Role;

public interface RoleMapper {

	public List<Role> selectAllRoles(HashMap<String, Object> map);
	
	public Role selectRoleById(String role_id);
	
	public int deleteRoleMenu(String role_id);
	
	public int insertRole(HashMap<String, Object> map);
	
	public int insertRoleMenu(HashMap<String, Object> map);
	
	public int updateRole(HashMap<String, Object> map);
	
	public int deleteRole(String role_id);
	
}
