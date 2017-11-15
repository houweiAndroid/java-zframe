package com.zss.zframe.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.zss.zframe.base.BaseController;
import com.zss.zframe.base.DataRes;
import com.zss.zframe.system.bean.Menu;
import com.zss.zframe.system.service.MenuService;
import com.zss.zframe.utils.JsonUtils;

/**
 * 系统菜单接口
 * 
 * @author zm
 */
@Controller
@RequestMapping("/system")
public class MenuController extends BaseController {

	@Resource
	private MenuService service;

	@RequestMapping("selectAllMenus.do")
	@ResponseBody
	public String selectAllMenus(HttpServletRequest request) {
		getHashMap(request);
		DataRes res = new DataRes();
		List<Menu> info = service.selectAllMenus(reqMap);
		if (info == null) {
			info = new ArrayList<Menu>();
		}
		res.setData(info);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("selectTreeMenus.do")
	@ResponseBody
	public String selectTreeMenus(HttpServletRequest request) {
		getHashMap(request);
		DataRes res = new DataRes();
		List<Menu> info = service.selectTreeMenus(reqMap);
		if (info == null) {
			info = new ArrayList<Menu>();
		}
		res.setData(info);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("selectListMenuByPId.do")
	@ResponseBody
	public String selectListMenuByPId(HttpServletRequest request) {
		getHashMap(request);
		String parent_id = getReqValue("parent_id");
		if (StringUtils.isEmpty(parent_id)) {
			parent_id = "0";
		}
		DataRes res = new DataRes();
		List<Menu> info = service.selectListMenuByPId(parent_id);
		if (info == null) {
			info = new ArrayList<Menu>();
		}
		res.setData(info);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("selectMenuById.do")
	@ResponseBody
	public String selectMenuById(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("menu_id");
		DataRes res = new DataRes();
		Menu Menu = service.selectMenuById(getReqValue("menu_id"));
		if (Menu == null) {
			Menu = new Menu();
		}
		res.setData(Menu);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("insertMenu.do")
	@ResponseBody
	public String insertMenu(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("menu_name");
		checkParamEmpty("menu_url");
		checkParamEmpty("parent_id");
		DataRes res = new DataRes();
		HashMap<String, Object> map = service.insertMenu(reqMap);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("updateMenu.do")
	@ResponseBody
	public String updateMenu(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("menu_id");
		checkParamEmpty("menu_name");
		checkParamEmpty("menu_url");
		checkParamEmpty("parent_id");
		DataRes res = new DataRes();
		int count = service.updateMenu(reqMap);
		Map<String, Object> map = new HashMap<>();
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}

	@RequestMapping("deleteMenu.do")
	@ResponseBody
	public String deleteMenu(HttpServletRequest request) {
		getHashMap(request);
		checkParamEmpty("menu_id");
		DataRes res = new DataRes();
		int count = service.deleteMenu(getReqValue("menu_id"));
		Map<String, Object> map = new HashMap<>();
		map.put("record_cnt", count);
		res.setData(map);
		return JsonUtils.objToJackson(res);
	}

}