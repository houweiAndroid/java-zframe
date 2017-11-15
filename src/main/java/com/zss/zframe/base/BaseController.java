package com.zss.zframe.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zss.zframe.Constants;
import com.zss.zframe.utils.JsonUtils;



public class BaseController {
	
public HashMap<String, Object> reqMap = new HashMap<>();
	
	public static final Logger log = LoggerFactory.getLogger(BaseController.class);

	public String buildError(String paramName) {
		DataRes result = new DataRes();
		result.setCode(Constants.REQ_PARAM_NULL);
		String msg = Constants.getError(Constants.REQ_PARAM_NULL);
		String error = String.format(msg, paramName);
		result.setError(error);
		return JsonUtils.objToJackson(result);
	}

	public String getReqValue(String paramName) {
		Object value = reqMap.get(paramName);
		if(value == null){
			return "";
		}else{
			return value.toString();
		}
	}

	public void checkParamEmpty(String paramName) {
		Object value = reqMap.get(paramName);
		if (value == null) {
			throw new RuntimeException(buildError(paramName));
		}
	}

	public HashMap<String, Object> getHashMap(HttpServletRequest request) {
		reqMap = new HashMap<String, Object>();
		Map maps = request.getParameterMap();
		Iterator entries = maps.entrySet().iterator();
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
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
		log.info("---------request param = " + reqMap);
		return reqMap;
	}

}
