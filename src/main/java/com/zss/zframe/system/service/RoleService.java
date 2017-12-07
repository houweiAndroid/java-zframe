package com.zss.zframe.system.service;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zss.zframe.system.bean.Role;

/**
 * 系统角色服务接口
 * @author zm
 *
 */
public interface RoleService {

	public PageInfo<Role> selectPageRoles(int page_index, int page_size, HashMap<String, Object> map);
	
	public List<Role> selectAllRoles(HashMap<String, Object> map);
	
	public Role selectRoleById(String role_id);
	
	public HashMap<String, Object> insertRole(HashMap<String, Object> map);
	
	public int updateRole(HashMap<String, Object> map);
	
	public int deleteRole(String role_id);
}
