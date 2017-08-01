package com.zss.zframe.system.mapper;

import java.util.HashMap;
import java.util.List;

import com.zss.zframe.system.bean.User;

public interface UserMapper {

	public List<User> selectAllUsers(HashMap<String, Object> map);
	
	public User selectUserById(String user_id);
	
	public int insertUser(HashMap<String, Object> map);
	
	public int updateUser(HashMap<String, Object> map);
	
	public int deleteUser(String user_id);
	
	public User sysLogin(String user_name);
	
}
