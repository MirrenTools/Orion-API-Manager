package org.mirrentools.orion.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mirrentools.orion.common.ColumnsProject;
import org.mirrentools.orion.common.ColumnsTags;
import org.mirrentools.orion.common.ColumnsUsers;
import org.mirrentools.orion.common.LoginRole;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.MD5Util;
import org.mirrentools.orion.common.ResultCode;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.SqlAssist;
import org.mirrentools.orion.common.SqlAssist.LimitResult;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.common.VerifyCodeSession;
import org.mirrentools.orion.common.VerifyCodeSessionStore;
import org.mirrentools.orion.entity.Tags;
import org.mirrentools.orion.entity.Users;
import org.mirrentools.orion.mapper.ProjectMapper;
import org.mirrentools.orion.mapper.TagsMapper;
import org.mirrentools.orion.mapper.UsersMapper;
import org.mirrentools.orion.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关的服务接口默认实现
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
@Service
public class DefaultUsersServiceImpl implements UsersService {
	private static final Logger LOG = LogManager.getLogger(DefaultUsersServiceImpl.class);
	/** 用户的SQL */
	@Autowired
	private UsersMapper usersMapper;
	/** 标签的SQL */
	@Autowired
	private TagsMapper tagsMapper;
	/** 项目的SQL */
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public Map<String, Object> login(String id, String pwd, String index, String value) {
		if (StringUtil.isNullOrEmpty(id, pwd, index, value)) {
			return ResultUtil.format(ResultCode.R412);
		}

		VerifyCodeSession verify = VerifyCodeSessionStore.get(index);
		if (verify == null || !value.equals(verify.getValue())) {
			return ResultUtil.format(ResultCode.R1304);
		}
		if (id.startsWith("X-")) {
			try {
				byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/config/user.json"));
				JSONObject users = new JSONObject(new String(bytes));
				if (users.has(id)) {
					JSONObject user = users.getJSONObject(id);
					if (user.has("pwd") && MD5Util.compare(user.getString("pwd"), 2, MD5Util.encode(pwd))) {
						String sessionId = MD5Util.encode(UUID.randomUUID().toString(), 3);
						LoginSessionStore.save(sessionId, id, LoginRole.ROOT);
						Map<String, String> result = new HashMap<>();
						result.put("uid", id);
						result.put("sessionId", sessionId);
						result.put("role", LoginRole.ROOT.name());
						return ResultUtil.format200(result);
					} else {
						return ResultUtil.format(ResultCode.R402);
					}
				} else {
					return ResultUtil.format(ResultCode.R402);
				}
			} catch (Exception e) {
				LOG.error("执行ROOT用户登录-->失败:", e);
				return ResultUtil.format(ResultCode.R555, e.getMessage());
			}
		} else {
			try {
				Users users = usersMapper.selectById(id);
				if (users == null || users.getUid() == null) {
					return ResultUtil.format(ResultCode.R402);
				} else {
					if (MD5Util.compare(pwd, users.getPwd())) {
						String sessionId = MD5Util.encode(UUID.randomUUID().toString(), 3);
						List<String> tags = new ArrayList<>();
						if (users.getTags() != null) {
							JSONArray array = new JSONArray(users.getTags());
							for (int i = 0; i < array.length(); i++) {
								tags.add(array.getString(i));
							}
						}
						LoginSessionStore.save(sessionId, id, LoginRole.valueOf(users.getRole()), tags);
						Map<String, String> result = new HashMap<>();
						result.put("uid", id);
						result.put("sessionId", sessionId);
						if (LoginRole.SERVER == LoginRole.valueOf(users.getRole())) {
							result.put("role", LoginRole.SERVER.name());
						} else {
							result.put("role", LoginRole.CLIENT.name());
						}
						// 更新最后登录时间
						Users user = new Users();
						user.setUid(id);
						user.setLasttime(System.currentTimeMillis());
						usersMapper.updateNotNullById(user);
						return ResultUtil.format200(result);
					} else {
						return ResultUtil.format(ResultCode.R402);
					}
				}
			} catch (Exception e) {
				LOG.error("执行用户登录-->失败:", e);
				return ResultUtil.format(ResultCode.R555, e.getMessage());
			}
		}
	}

	@Override
	public Map<String, Object> logout(String sessionId) {
		try {
			LoginSessionStore.remove(sessionId);
			return ResultUtil.format(ResultCode.R200);
		} catch (Exception e) {
			LOG.error("执行退出登录-->失败:", e);
			return ResultUtil.format(ResultCode.R200);
		}
	}

	@Override
	public Map<String, Object> putPassword(String id, String pwd) {
		System.out.println(id);
		System.out.println(pwd);
		if (StringUtil.isNullOrEmpty(id, pwd)) {
			return ResultUtil.format(ResultCode.R412, "请按要求填写所有必填项");
		}
		try {
			Users user = new Users();
			user.setUid(id);
			user.setPwd(MD5Util.encode(pwd));
			int result = usersMapper.updateNotNullById(user);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行修改密码->\n" + id + "-->" + pwd + "\n-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> findUsers(String keywords, String tid, Integer page, Integer size) {
		try {
			SqlAssist assist = new SqlAssist();
			if (!StringUtil.isNullOrEmpty(keywords)) {
				assist.orLike(ColumnsUsers.UID, "%" + keywords + "%");
				assist.orLike(ColumnsUsers.NICKNAME, "%" + keywords + "%");
				assist.orLike(ColumnsUsers.SUMMARY, "%" + keywords + "%");
			}
			if (!StringUtil.isNullOrEmpty(tid)) {
				assist.orLike(ColumnsUsers.TAGS, "%\"" + tid + "\"%");
			}
			assist.setPage(page).setRowSize(size);
			assist.setResultColumn(String.format("%s,%s,%s,%s,%s,%s,%s", ColumnsUsers.UID, ColumnsUsers.ROLE,
					ColumnsUsers.TAGS, ColumnsUsers.NICKNAME, ColumnsUsers.CONTACT, ColumnsUsers.SUMMARY, ColumnsUsers.LASTTIME));
			LimitResult<Users> result = usersMapper.limitAll(assist);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行获取用户列表-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}

	}

	@Override
	public Map<String, Object> findServerUsers() {
		SqlAssist assist = new SqlAssist();
		assist.andEq(ColumnsUsers.ROLE, LoginRole.SERVER.name());
		assist.setResultColumn(String.format("%s,%s", ColumnsUsers.UID, ColumnsUsers.NICKNAME));
		List<Users> all = usersMapper.selectAll(assist);
		List<Map<String, String>> result = new ArrayList<>();
		if (all != null) {
			for (Users user : all) {
				Map<String, String> map = new HashMap<>();
				map.put("uid", user.getUid());
				map.put("nickname", user.getNickname());
				result.add(map);
			}
		}
		return ResultUtil.format200(result);
	}

	@Override
	public Map<String, Object> getUser(String uid) {
		try {
			if (StringUtil.isNullOrEmpty(uid)) {
				return ResultUtil.format(ResultCode.R412, "用户的id不能为空");
			}
			Users users = usersMapper.selectById(uid);
			if (users != null) {
				users.setPwd(null);
			}
			return ResultUtil.format200(users);
		} catch (Exception e) {
			LOG.error("执行获取指定用户-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> postUser(String sessionId, Users user) {
		try {
			if (user == null || StringUtil.isNullOrEmpty(sessionId, user.getUid(), user.getRole(), user.getPwd(),
					user.getNickname(), user.getContact())) {
				return ResultUtil.format(ResultCode.R412, "请按要求填写所有必填项");
			}
			LoginRole userRole = getUserRole(sessionId);
			if (userRole == null) {
				return ResultUtil.format(ResultCode.R1003, "无效的角色!");
			}
			LoginRole role = LoginRole.valueOf(user.getRole());
			if (role != LoginRole.CLIENT) {
				if (role == LoginRole.SERVER) {
					if (userRole != LoginRole.ROOT) {
						return ResultUtil.format(ResultCode.R1002, "普通管理员无法创建管理员角色的用户!");
					}
				} else {
					return ResultUtil.format(ResultCode.R1003, "无效的角色!");
				}
			}
			if (user.getUid().startsWith("X-")) {
				return ResultUtil.format(ResultCode.R1002, "普通管理员无法创建管理员角色的用户!");
			}
			Users checkUser = usersMapper.selectById(user.getUid());
			if (checkUser != null && checkUser.getUid() != null) {
				return ResultUtil.format(ResultCode.R1001, "该用户的账号已存在!");
			}
			user.setPwd(MD5Util.encode(user.getPwd()));
			int result = usersMapper.insertNotNull(user);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行新增用户->\n" + user + "\n-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> putUser(String sessionId, Users user) {
		try {
			if (user == null || StringUtil.isNullOrEmpty(sessionId, user.getUid(), user.getRole(), user.getNickname(),
					user.getContact())) {
				return ResultUtil.format(ResultCode.R412, "请按要求填写所有必填项");
			}
			LoginRole userRole = getUserRole(sessionId);
			LoginRole role = LoginRole.valueOf(user.getRole());
			if (role != LoginRole.CLIENT) {
				if (role == LoginRole.SERVER) {
					if (userRole != LoginRole.ROOT) {
						return ResultUtil.format(ResultCode.R1002, "普通管理员无法创建管理员角色的用户!");
					}
				} else {
					return ResultUtil.format(ResultCode.R1003, "无效的角色!");
				}
			}
			if (!StringUtil.isNullOrEmpty(user.getPwd())) {
				user.setPwd(MD5Util.encode(user.getPwd()));
			}
			int result = usersMapper.updateNotNullById(user);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行修改用户->\n" + user + "\n-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}

	}

	@Override
	public Map<String, Object> deleteUser(String sessionId, String uid) {
		try {
			if (StringUtil.isNullOrEmpty(sessionId, uid)) {
				return ResultUtil.format(ResultCode.R412, "请按要求填写所有必填项");
			}
			LoginRole userRole = getUserRole(sessionId);
			if (userRole == LoginRole.ROOT) {
				int result = usersMapper.deleteById(uid);
				return ResultUtil.format200(result);
			} else if (userRole == LoginRole.SERVER) {
				SqlAssist assist = new SqlAssist();
				assist.andEq(ColumnsUsers.ROLE, LoginRole.CLIENT.name());
				assist.andEq(ColumnsUsers.UID, uid);
				int result = usersMapper.deleteByAssist(assist);
				if (result == 0) {
					return ResultUtil.format(ResultCode.R1010, "删除失败,管理员只能删除普通用户");
				}
				return ResultUtil.format200(result);
			} else {
				return ResultUtil.format(ResultCode.R1011, "只有管理员可以删除用户");
			}
		} catch (Exception e) {
			LOG.error("执行删除用户->" + uid + "-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> findTags() {
		try {
			SqlAssist assist = new SqlAssist()
					.setOrderBy(String.format("%s asc,%s desc", ColumnsTags.SORTS, ColumnsTags.CTIME));
			List<Tags> all = tagsMapper.selectAll(assist);
			return ResultUtil.format200(all);
		} catch (Exception e) {
			LOG.error("执行获取所有标签-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getTag(String tid) {
		try {
			if (StringUtil.isNullOrEmpty(tid)) {
				return ResultUtil.format(ResultCode.R412, "标签的id不能为空");
			}
			Tags result = tagsMapper.selectById(tid);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行获取指定标签->" + tid + "-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> postTag(Tags tag) {
		try {
			if (tag == null || StringUtil.isNullOrEmpty(tag.getTname())) {
				return ResultUtil.format(ResultCode.R412, "标签的名称不能为空");
			}
			if (tag.getTid() == null) {
				tag.setTid(UUID.randomUUID().toString());
			}
			if (tag.getSorts() == null) {
				tag.setSorts(0);
			}
			tag.setCtime(System.currentTimeMillis());
			int result = tagsMapper.insertNotNull(tag);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行新增标签->\n" + tag + "\n-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> putTag(Tags tag) {
		try {
			System.out.println(tag);
			if (tag == null || StringUtil.isNullOrEmpty(tag.getTid())) {
				return ResultUtil.format(ResultCode.R412, "标签的id不能为空");
			}
			tag.setCtime(System.currentTimeMillis());
			int result = tagsMapper.updateNotNullById(tag);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行修改标签->\n" + tag + "\n-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	@Override
	public Map<String, Object> deleteTag(String tid) {
		try {
			SqlAssist pAssist = new SqlAssist();
			pAssist.andLike(ColumnsProject.OWNERS, "%\"" + tid + "\"%");
			long ps = projectMapper.getCount(pAssist);
			if (ps > 0) {
				return ResultUtil.format(ResultCode.R1100, "该标签已被项目引用,无法进行删除");
			}
			SqlAssist uAssist = new SqlAssist();
			uAssist.andLike(ColumnsUsers.TAGS, "%\"" + tid + "\"%");
			long us = usersMapper.getCount(uAssist);
			if (us > 0) {
				return ResultUtil.format(ResultCode.R1101, "该标签下有用户,无法进行删除");
			}
			int result = tagsMapper.deleteById(tid);
			return ResultUtil.format200(result);
		} catch (Exception e) {
			LOG.error("执行删除标签->" + tid + "-->失败:", e);
			return ResultUtil.format(ResultCode.R555, e.getMessage());
		}
	}

	/**
	 * 获取用户的角色
	 * 
	 * @param sessionId
	 * @return
	 */
	private LoginRole getUserRole(String sessionId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		if (session == null) {
			return null;
		}
		return session.getRole();
	}

}
