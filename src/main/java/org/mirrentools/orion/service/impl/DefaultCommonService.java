package org.mirrentools.orion.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.mirrentools.orion.common.OrionApiManager;
import org.mirrentools.orion.common.ResultCode;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.service.CommonService;
import org.mirrentools.orion.service.DocsConverter;
import org.mirrentools.orion.service.DocsConverterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 通用服务的默认实现
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@Service
public class DefaultCommonService implements CommonService {
	@Value("${orionConsoleTitle}")
	private String orionConsoleTitle;
	@Value("${orionConsoleWelcome}")
	private String orionConsoleWelcome;
	@Override
	public Map<String, Object> getConsoleConfig() {
		Map<String, Object> result = new HashMap<>();
		result.put("title", (StringUtil.isNullOrEmpty(orionConsoleTitle) ? OrionApiManager.NAME : orionConsoleTitle));
		result.put("welcome", (StringUtil.isNullOrEmpty(orionConsoleWelcome) ? OrionApiManager.WELCOME : orionConsoleWelcome));
		result.put("name", OrionApiManager.NAME);
		result.put("version", OrionApiManager.VERSION);
		return ResultUtil.format200(result);
	}
	@Override
	public Map<String, Object> checkConvert(String type) {
		DocsConverter converter = DocsConverterFactory.get(type);
		if (converter == null) {
			return ResultUtil.format200(0);
		} else {
			return ResultUtil.format200(1);
		}
	}

	@Override
	public Map<String, Object> convertApi(String type, String body) {
		if (StringUtil.isNullOrEmpty(type, body)) {
			return ResultUtil.format(ResultCode.R412);
		}
		DocsConverter converter = DocsConverterFactory.get(type);
		if (converter == null) {
			return ResultUtil.format(ResultCode.R413);
		}
		String result = converter.convert(body);
		return ResultUtil.format200(result);
	}

}
