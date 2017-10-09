package com.zss.zframe.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	private static ObjectMapper jacksonMapper = new ObjectMapper();
	
	static{
		jacksonMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
	}

	public static String objToJackson(Object obj) {
		try {
			return jacksonMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T jacksonToObj(String src, Class<?> collectionClass, Class<?>... valueType) {
		try {
			JavaType javaType = jacksonMapper.getTypeFactory().constructParametricType(collectionClass, valueType);
			return (T) jacksonMapper.readValue(src, javaType);
		} catch (Exception e) {
			return null;
		}
	}
	
}
