package org.mirrentools.orion.service;

import java.util.Map;

import org.mirrentools.orion.entity.Tags;
import org.mirrentools.orion.entity.Users;

/**
 * 用户相关的服务接口
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public interface UsersService {
	/**
	 * 登录
	 * 
	 * @return
	 */
	Map<String, Object> login(String id, String pwd, String index, String value);

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	Map<String, Object> logout(String sessionId);

	/**
	 * 获取用户
	 * 
	 * @param keywords 搜索的关键字
	 * @param role     所在的标签
	 * @param page     第几页
	 * @param size     每页获取多少行数据
	 * @return
	 */
	Map<String, Object> findUsers(String keywords, String tid, Integer page, Integer size);

	/**
	 * 获取所有管理员用户
	 * 
	 * @return
	 */
	Map<String, Object> findServerUsers();

	/**
	 * 获取指定用户
	 * 
	 * @param uid
	 * @return
	 */
	Map<String, Object> getUser(String uid);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	Map<String, Object> postUser(String sessionId, Users user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	Map<String, Object> putUser(String sessionId, Users user);

	/**
	 * 删除用户
	 * 
	 * @param uid
	 * @return
	 */
	Map<String, Object> deleteUser(String sessionId, String uid);

	/**
	 * 获取所有标签
	 * 
	 * @return
	 */
	Map<String, Object> findTags();

	/**
	 * 获取指定标签
	 * 
	 * @param tid
	 * @return
	 */
	Map<String, Object> getTag(String tid);

	/**
	 * 新增标签
	 * 
	 * @param tag
	 * @return
	 */
	Map<String, Object> postTag(Tags tag);

	/**
	 * 修改指定标签
	 * 
	 * @param tag
	 * @return
	 */
	Map<String, Object> putTag(Tags tag);

	/**
	 * 删除指定标签
	 * 
	 * @param tid
	 * @return
	 */
	Map<String, Object> deleteTag(String tid);

}
