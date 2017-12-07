package com.zss.zframe.system.service;

import java.util.HashMap;

import com.github.pagehelper.PageInfo;
import com.zss.zframe.system.bean.User;

/**
 * 系统用户服务接口
 * @author zm
 *
 */
public interface UserService {

	public PageInfo<User> selectPageUsers(int page_index, int page_size, HashMap<String, Object> map);
	
	public User selectUserById(String user_id);
	
	public HashMap<String, Object> insertUser(HashMap<String, Object> map);
	
	public int updateUser(HashMap<String, Object> map);
	
	public int deleteUser(String user_id);
	
	public User sysLogin(String user_name);
	
}
