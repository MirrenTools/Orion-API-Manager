package org.mirrentools.orion.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.mirrentools.orion.service.impl.OpenApiConvertToOrionDocs;
/**
 * 转换工具的实例工程工厂
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class DocsConverterFactory {
	/** 存储实例的map */
	private static Map<String, DocsConverter> INSTANCES = new LinkedHashMap<>();
	/** OpenAPI文档转换器的名称 */
	private static final String OpenAPI = "OpenAPI";
	static {
		INSTANCES.put(OpenAPI, new OpenApiConvertToOrionDocs());
	}

	/**
	 * 获取指定实例
	 * 
	 * @param name
	 *          对应names中的名称
	 * @return
	 */
	public static DocsConverter get(String name) {
		return INSTANCES.get(name);
	}
	/**
	 * 获取所有实例的名称
	 * 
	 * @return
	 */
	public static Set<String> names() {
		return INSTANCES.keySet();
	}

}
