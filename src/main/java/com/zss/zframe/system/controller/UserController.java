package com.zss.zframe.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.DataRes;
import com.zss.zframe.system.bean.Menu;
import com.zss.zframe.system.bean.User;
import com.zss.zframe.system.service.MenuService;
import com.zss.zframe.system.service.UserService;
import com.zss.zframe.utils.JsonUtils;
import com.zss.zframe.utils.StrUtils;


/**
 * 系统用户接口
 * @author zm
 */
@Controller
@RequestMapping("/system")
public class UserController extends BaseController {

	public final String intPwd = "111111";
	
	@Resource
	private UserService service;
	
	@Resource
	private MenuService menuService;

	@RequestMapping("selectPageUsers")
	@ResponseBody
	public String selectPageUsers(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("page_index");
		checkParamEmpty("page_size");
		DataRes res = new DataRes();
		PageInfo<User> info = service.selectPageUsers(
				Integer.parseInt(getReqValue("page_index")),
				Integer.parseInt(getReqValue("page_size")), reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("totalCount", info.getTotal());
		map.put("list", info.getList());
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectUserById")
	@ResponseBody
	public String selectUserById(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("user_id");
		DataRes res = new DataRes();
		User user = service.selectUserById(getReqValue("user_id"));
		if(user == null){
			user = new User();
		}
		res.setData(user);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("insertUser")
	@ResponseBody
	public String insertUser(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("user_name");
		if (StrUtils.isEmpty(getReqValue("password"))) {
			reqMap.put("password", intPwd);
		}
		checkParamEmpty("role_ids");
		DataRes res = new DataRes();
		HashMap<String, Object> map = service.insertUser(reqMap);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public String updateUser(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("user_id");
		checkParamEmpty("user_name");
		checkParamEmpty("role_ids");
		DataRes res = new DataRes();
		int count = service.updateUser(reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public String deleteUser(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("user_id");
		DataRes res = new DataRes();
		int count = service.deleteUser(getReqValue("user_id"));
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("sysLogin")
	@ResponseBody
	public String sysLogin(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("user_name");
		checkParamEmpty("password");
		DataRes res = new DataRes();
		User user = service.sysLogin(getReqValue("user_name"));
		if(user != null && user.getUser_id().compareTo("0") > 0){
			if(user.getPassword().equals(getReqValue("password"))){
				List<Menu> menus = menuService.selectUserMenu(user.getUser_id());
				user.setMenus(menus);
				user.setPassword("");
				HttpSession http = request.getSession();
				http.setAttribute("sys_user", user);
				res.setData(user);
				return JsonUtils.objToJackson(res);
			} else{
				res.setCodeAndError(1002);
				return JsonUtils.objToJackson(res);
			}
		}
		res.setCodeAndError(1003);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("isLogin")
	@ResponseBody
	public String isLogin(HttpServletRequest request) {
		DataRes res = new DataRes();
		HttpSession http = request.getSession();
		Object obj = http.getAttribute("sys_user");
		if(obj != null){
			User user = (User)obj;
			res.setData(user);
			return JsonUtils.objToJackson(res);
		} else {
			res.setCodeAndError(1004);
			return JsonUtils.objToJackson(res);
		}
	}

}