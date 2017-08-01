package com.zss.zframe.system.service.impl;

import java.util.HashMap;
import java.util.List;

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
	public PageInfo<List<User>> selectAllUsers(int page_index, int page_size, HashMap<String, Object> map) {
		 PageHelper.startPage(page_index, page_size);
		 return new PageInfo(userMapper.selectAllUsers(map));
	}

	@Override
	public User selectUserById(String user_id) {
		return userMapper.selectUserById(user_id);
	}

	@Override
	@Transactional()
	public HashMap<String, Object> insertUser(HashMap<String, Object> map) {
		userMapper.insertUser(map);
		return map;
	}

	@Override
	public int updateUser(HashMap<String, Object> map) {
		return userMapper.updateUser(map);
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
