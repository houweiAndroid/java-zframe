package com.zss.zframe.system.bean;

import java.util.List;

/**
 * 角色管理
 * @author zm
 *
 */
public class Menu {

	private int menu_id; //菜单ID
	private String menu_name; //菜单名称
	private String menu_url; //菜单路径
	private int parent_id; //父ID
	private int menu_type; //菜单类型
	private int menu_order; //菜单排序
	private int state; //状态 0正常 -1废弃
	private List<Menu> subs;
	
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(int menu_type) {
		this.menu_type = menu_type;
	}
	public int getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<Menu> getSubs() {
		return subs;
	}
	public void setSubs(List<Menu> subs) {
		this.subs = subs;
	}
	
}
