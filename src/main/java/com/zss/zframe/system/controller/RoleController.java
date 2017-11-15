package com.zss.zframe.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.DataRes;
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
	public String selectAllRoles(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("page_index");
		checkParamEmpty("page_size");
		DataRes res = new DataRes();
		PageInfo<List<Role>> info = service.selectAllRoles(
				Integer.parseInt(getReqValue("page_index")),
				Integer.parseInt(getReqValue("page_size")), reqMap);
		if (info == null) {
			info = new PageInfo<List<Role>>();
		}
		Map<String, Object> map = new HashMap<>(); 
		map.put("totalCount", info.getTotal());
		map.put("list", info.getList());
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectRoleById.do")
	@ResponseBody
	public String selectRoleById(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("role_id");
		DataRes res = new DataRes();
		Role role = service.selectRoleById(getReqValue("role_id"));
		if(role == null){
			role = new Role();
		}
		res.setData(role);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("insertRole.do")
	@ResponseBody
	public String insertRole(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("role_name");
		checkParamEmpty("menu_ids");
		DataRes res = new DataRes();
		HashMap<String, Object> map = service.insertRole(reqMap);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("updateRole.do")
	@ResponseBody
	public String updateRole(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("role_id");
		checkParamEmpty("role_name");
		checkParamEmpty("menu_ids");
		DataRes res = new DataRes();
		int count = service.updateRole(reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("deleteRole.do")
	@ResponseBody
	public String deleteRole(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("role_id");
		DataRes res = new DataRes();
		int count = service.deleteRole(getReqValue("role_id"));
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}
	
}