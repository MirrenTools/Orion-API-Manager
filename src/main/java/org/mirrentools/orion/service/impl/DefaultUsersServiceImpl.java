package org.mirrentools.orion.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.mirrentools.orion.common.LoginRole;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.MD5Util;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * 用户相关的服务接口默认实现
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
@Service
public class DefaultUsersServiceImpl implements UsersService {

	@Override
	public Map<String, Object> login(String id, String pwd) {
		if (StringUtil.isNullOrEmpty(id, pwd)) {
			return ResultUtil.failed("登录失败,账号与密码不能为空!", 0);
		}
		if (id.startsWith("X-")) {
			try {
				byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/config/user.json"));
				JSONObject users = new JSONObject(new String(bytes));
				if (users.has(id)) {
					JSONObject user = users.getJSONObject(id);
					if (user.has("pwd") && user.get("pwd").equals(pwd)) {
						String sessionId = MD5Util.encode(UUID.randomUUID().toString(), 3);
						LoginSessionStore.save(sessionId, id, LoginRole.ROOT);
						Map<String, String> result = new HashMap<>();
						result.put("uid", id);
						result.put("sessionId", sessionId);
						result.put("role", LoginRole.ROOT.name());
						return ResultUtil.succeed(result);
					} else {
						return ResultUtil.failed("登录失败,账号或密码错误!", 0);
					}
				} else {
					return ResultUtil.failed("登录失败,账号或密码错误!", 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtil.failed(e.getMessage(), 0);
			}
		} else {
			return ResultUtil.failed("无效的账号", 0);
		}
	}

	@Override
	public Map<String, Object> logout(String sessionId) {
		LoginSessionStore.remove(sessionId);
		return ResultUtil.succeed(1);
	}

}
