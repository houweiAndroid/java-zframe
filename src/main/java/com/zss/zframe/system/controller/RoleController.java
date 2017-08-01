package com.zss.zframe.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.ObjectHttpRes;
import com.zss.zframe.base.PageHttpRes;
import com.zss.zframe.system.bean.Role;
import com.zss.zframe.system.service.RoleService;
import com.zss.zframe.utils.JsonUtils;


/**
 * 系统角色接口
 * @author zm
 */
@Controller
@RequestMapping("/system")
public class RoleController extends BaseController {

	@Resource
	private RoleService service;

	@RequestMapping("selectAllRoles.do")
	@ResponseBody
	public String selectAllRoles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("page_index")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("page_size")) {
			return getErrorMsg();
		}
		PageHttpRes res = new PageHttpRes();
		PageInfo<List<Role>> info = service.selectAllRoles(
				Integer.parseInt(getMapValue("page_index")),
				Integer.parseInt(getMapValue("page_size")), reqMap);
		if (info == null) {
			info = new PageInfo<List<Role>>();
		}
		res.setTotal(info.getTotal());
		res.setResult(info.getList());
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectRoleById.do")
	@ResponseBody
	public String selectRoleById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("role_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		Role role = service.selectRoleById(getMapValue("role_id"));
		if(role == null){
			role = new Role();
		}
		res.setResult(role);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("insertRole.do")
	@ResponseBody
	public String insertRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("role_name")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("menu_ids")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		HashMap<String, Object> map = service.insertRole(reqMap);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("updateRole.do")
	@ResponseBody
	public String updateRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("role_id")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("role_name")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("menu_ids")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.updateRole(reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("deleteRole.do")
	@ResponseBody
	public String deleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("role_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.deleteRole(getMapValue("role_id"));
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
}