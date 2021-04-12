package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用控制器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = {"x-session"}, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS,
		RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE,})
@RestController
public class CommonController {
	/** 接口服务 */
	@Autowired
	private CommonService service;
	/**
	 * 获取控制台配置信息
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/config/console", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getConsoleConfig(String id, String pwd, String index, String value) {
		Map<String, Object> result = service.getConsoleConfig();
		return result;
	}
//	/**
//	 * 检查转换器是否可用
//	 * 
//	 * @return
//	 */
//	@PostMapping(value = "/public/checkconverter", produces = {"application/json;charset=UTF-8"})
//	public Map<String, Object> checkConvert(String type) {
//		return service.checkConvert(type);
//	}
//
//	/**
//	 * 将其他接口文档转换为OrionAPI文档
//	 * 
//	 * @param type
//	 * @param body
//	 * @return
//	 */
//	@PostMapping(value = "/public/convertApiDocs", produces = {"application/json;charset=UTF-8"})
//	public Map<String, Object> convert(String type, String body) {
//		return service.convertApi(type, body);
//	}

}
