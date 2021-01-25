package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
