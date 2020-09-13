package org.mirrentools.orion.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.common.VerifyCodeSessionStore;
import org.mirrentools.orion.common.VerifyCodeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码相关的控制器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = {"x-session", "content-type"}, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.HEAD,
		RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE,})
@RestController
public class VerifyCodeController {
	private static final Logger LOG = LogManager.getLogger(VerifyCodeController.class);

	/**	
	 * 获取验证码数据
	 * 
	 * @return
	 */
	@GetMapping(value = "/verification/data", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getdata() {
		try {
			Map<String, String> verify = VerifyCodeUtils.generateVerityData();
			String index = verify.get("index");
			String data = verify.get("data");
			String code = verify.get("code");
			VerifyCodeSessionStore.save(index, verify.get("value"), data, code);
			Map<String, Object> result = new HashMap<>();
			result.put("index", index);
			result.put("data", data);
			result.put("code", code);
			return result;
		} catch (Exception e) {
			LOG.error("执行生成验证码数据-->失败:", e);
			return ResultUtil.failed(e == null ? "" : e.getMessage());
		}
	}
	@GetMapping(value = "/verification/img/{code}", produces = {"image/jpeg"})
	public byte[] getImg(@PathVariable("code") String code) {
		try {
			if (StringUtil.isNullOrEmpty(code)) {
				return null;
			}
			byte[] bs = VerifyCodeUtils.getImageBytes(code);
			return bs;
		} catch (IOException e) {
			LOG.error("执行生成验证码图片-->失败:", e);
			return null;
		}
	}
}
