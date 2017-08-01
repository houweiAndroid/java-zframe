package com.zss.zframe.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.ObjectHttpRes;
import com.zss.zframe.system.bean.Menu;
import com.zss.zframe.system.service.MenuService;
import com.zss.zframe.utils.JsonUtils;

/**
 * 系统菜单接口
 * @author zm
 */
@Controller
@RequestMapping("/system")
public class MenuController extends BaseController {
	
	@Resource
	private MenuService service;

	@RequestMapping("selectAllMenus.do")
	@ResponseBody
	public String selectAllMenus(HttpServletRequest request, HttpSession session) throws Exception {
		getHashMap(request);
		log.info("-------- session = " + session.getId());
		ObjectHttpRes res = new ObjectHttpRes();
		List<Menu> info = service.selectAllMenus(reqMap);
		if (info == null) {
			info = new ArrayList<Menu>();
		}
		res.setResult(info);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectTreeMenus.do")
	@ResponseBody
	public String selectTreeMenus(HttpServletRequest request, HttpSession session) throws Exception {
		getHashMap(request);
		ObjectHttpRes res = new ObjectHttpRes();
		List<Menu> info = service.selectTreeMenus(reqMap);
		if (info == null) {
			info = new ArrayList<Menu>();
		}
		res.setResult(info);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectListMenuByPId.do")
	@ResponseBody
	public String selectListMenuByPId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		String parent_id = getMapValue("parent_id");
		if (StringUtils.isEmpty(parent_id)) {
			parent_id = "0";
		}
		ObjectHttpRes res = new ObjectHttpRes();
		List<Menu> info = service.selectListMenuByPId(parent_id);
		if(info == null){
			info = new ArrayList<Menu>();
		}
		res.setResult(info);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("selectMenuById.do")
	@ResponseBody
	public String selectMenuById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("menu_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		Menu Menu = service.selectMenuById(getMapValue("menu_id"));
		if(Menu == null){
			Menu = new Menu();
		}
		res.setResult(Menu);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("insertMenu.do")
	@ResponseBody
	public String insertMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("menu_name")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("menu_url")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("parent_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		HashMap<String, Object> map = service.insertMenu(reqMap);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("updateMenu.do")
	@ResponseBody
	public String updateMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("menu_id")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("menu_name")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("menu_url")) {
			return getErrorMsg();
		}
		if (!checkParamEmpty("parent_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.updateMenu(reqMap);
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
	@RequestMapping("deleteMenu.do")
	@ResponseBody
	public String deleteMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getHashMap(request);
		if (!checkParamEmpty("menu_id")) {
			return getErrorMsg();
		}
		ObjectHttpRes res = new ObjectHttpRes();
		int count = service.deleteMenu(getMapValue("menu_id"));
		Map<String, Object> map = new HashMap<>(); 
		map.put("record_cnt", count);
		res.setResult(map);
		return JsonUtils.objToJackson(res);
	}
	
}