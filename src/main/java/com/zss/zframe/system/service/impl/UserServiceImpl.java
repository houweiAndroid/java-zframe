package com.zss.zframe.system.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zss.zframe.system.bean.User;
import com.zss.zframe.system.mapper.UserMapper;
import com.zss.zframe.system.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserMapper userMapper;
	
	@Override
	public PageInfo<User> selectPageUsers(int page_index, int page_size, HashMap<String, Object> map) {
		 int count = userMapper.selectUserCount(map);
		 PageHelper.startPage(page_index, page_size, false);
		 PageInfo<User> info = new PageInfo<User>(userMapper.selectAllUsers(map));
		 info.setTotal(count);
		 return info;
	}

	@Override
	public User selectUserById(String user_id) {
		return userMapper.selectUserById(user_id);
	}

	@Override
	@Transactional()
	public HashMap<String, Object> insertUser(HashMap<String, Object> map) {
		userMapper.insertUser(map);
		Long userId = (Long) map.get("user_id");
		if(userId > 0){
			String roleIds = map.get("role_ids").toString();
			String roleArr[] = roleIds.split(",");
			for(String item: roleArr){
				HashMap<String, Object> roleMap = new HashMap<>();
				roleMap.put("user_id", userId);
				roleMap.put("role_id", item);
				userMapper.insertUserRole(roleMap);
			}
		}
		return map;
	}

	@Override
	public int updateUser(HashMap<String, Object> map) {
		int cnt = userMapper.updateUser(map);
		String userId = (String) map.get("user_id");
		userMapper.deleteUserRole(userId);
		String roleIds = map.get("role_ids").toString();
		String roleArr[] = roleIds.split(",");
		for(String item: roleArr){
			HashMap<String, Object> roleMap = new HashMap<>();
			roleMap.put("user_id", userId);
			roleMap.put("role_id", item);
			userMapper.insertUserRole(roleMap);
		}
		return cnt;
	}

	@Override
	public int deleteUser(String user_id) {
		return userMapper.deleteUser(user_id);
	}

	@Override
	public User sysLogin(String user_name) {
		return userMapper.sysLogin(user_name);
	}
	
}
