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

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.ObjectHttpRes;
import com.zss.zframe.base.PageHttpRes;
import com.zss.zframe.system.bean.Menu;
import com.zss.zframe.system.bean.User;
import com.zss.zframe.system.service.MenuService;
import com.zss.zframe.system.service.UserService;
import com.zss.zframe.utils.JsonUtils;


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

	@RequestMapping("selectAllUsers.do")
	@ResponseBody
	public String selectAllUsers(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("page_index")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("page_size")) {
			return getErrorMsg();
		}
		PageHttpRes res = new PageHttpRes();
		PageInfo<List<User>> info = service.selectAllUsers(
				Integer.parseInt(getMapValue("page_index")),
				Integer.parseInt(getMapValue("page_size")), reqMap);
		if (info == null) {
			info = new PageInfo<List<User>>();
		}
		res.setTotal(info.getTotal());
		res.setResult(info.getList());
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectUserById.do")
	@ResponseBody
	public String selectUserById(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("user_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		User user = service.selectUserById(getMapValue("user_id"));
		if(user == null){
			user = new User();
		}
		res.setResult(user);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("insertUser.do")
	@ResponseBody
	public String insertUser(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("user_name")) {
			return getErrorMsg();
		}
		if (StringUtils.isEmpty(getMapValue("password"))) {
			reqMap.put("password", intPwd);
		}
		ObjectHttpRes res = new ObjectHttpRes();
		HashMap<String, Object> map = service.insertUser(reqMap);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("updateUser.do")
	@ResponseBody
	public String updateUser(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("user_id")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("user_name")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.updateUser(reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("deleteUser.do")
	@ResponseBody
	public String deleteUser(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("user_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.deleteUser(getMapValue("user_id"));
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("sysLogin.do")
	@ResponseBody
	public String sysLogin(HttpServletRequest request) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("user_name")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("password")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		User user = service.sysLogin(getMapValue("user_name"));
		if(user != null && user.getUser_id().compareTo("0") > 0){
			if(user.getPassword().equals(getMapValue("password"))){
				List<Menu> menus = menuService.selectUserMenu(user.getUser_id());
				user.setMenus(menus);
				user.setPassword("");
				HttpSession http = request.getSession();
				http.setAttribute("sys_user", user);
				res.setResult(user);
				return JsonUtils.objToJackson(res);
			} else{
				return getErrorJson("密码不正确");
			}
		}
		return getErrorJson("用户不存在");
	}
	
	@RequestMapping("isLogin.do")
	@ResponseBody
	public String isLogin(HttpServletRequest request) throws Exception {
		ObjectHttpRes res = new ObjectHttpRes();
		HttpSession http = request.getSession();
		Object obj = http.getAttribute("sys_user");
		if(obj != null){
			User user = (User)obj;
			res.setResult(user);
			return JsonUtils.objToJackson(res);
		} else {
			return getErrorJson("会话超时");
		}
	}

}