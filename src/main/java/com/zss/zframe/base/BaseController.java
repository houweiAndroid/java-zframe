package com.zss.zframe.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zss.zframe.system.controller.MenuController;
import com.zss.zframe.utils.JsonUtils;

public class BaseController {

	private String errorMsg;
	
	public HashMap<String, Object> reqMap = new HashMap<>();
	
	public static final Logger log = LoggerFactory.getLogger(MenuController.class);

	public String getErrorJson(String error) {
		ObjectHttpRes result = new ObjectHttpRes();
		result.setRet_code(-1);
		result.setRet_error(error);
		return JsonUtils.objToJackson(result);
	}

	public String getMapValue(String paramName) {
		Object value = reqMap.get(paramName);
		if(value == null){
			return "";
		}else{
			return value.toString();
		}
	}

	public boolean checkParamEmpty(String paramName) {
		Object value = reqMap.get(paramName);
		if (value == null) {
			setErrorMsg(getErrorJson(("'" + paramName + "'差数不能为空")));
			return false;
		}
		return true;
	}

	public HashMap<String, Object> getHashMap(HttpServletRequest request) {
		reqMap = new HashMap<String, Object>();
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			reqMap.put(name, value);
		}
		log.debug("---------reqMap = " + reqMap);
		return reqMap;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
