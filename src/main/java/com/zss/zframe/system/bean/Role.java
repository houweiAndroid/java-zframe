package com.zss.zframe.system.bean;

import java.util.List;

/**
 * 角色管理
 * @author zm
 *
 */
public class Role {

	private int role_id; //角色ID
	private String role_name; //角色名称
	private int state; //状态 0正常 -1废弃
	private List<Menu> menus; //菜单
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
