package org.mirrentools.orion;

import org.mirrentools.sd.SdType;
import org.mirrentools.sd.models.SdBean;
import org.mirrentools.sd.models.SdColumn;

/**
 * 常量信息比如数据库连接等,不同数据库一些关键字的字段需要做一下处理,比如MySQL需要将key改成`key`
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class Constant {
	/**
	 * 获取项目的Bean
	 * 
	 * @return
	 */
	public static SdBean getProjectBean() {
		SdBean bean = new SdBean();
		bean.setName("project").setRemark("项目表");
		bean.addColumn(
				new SdColumn().setName("key").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("项目的id"));
		bean.addColumn(
				new SdColumn().setName("name").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("名称"));
		bean.addColumn(new SdColumn().setName("versions").setType(SdType.STRING).setLength(60).setRemark("版本号"));
		bean.addColumn(new SdColumn().setName("description").setType(SdType.TEXT).setRemark("描述"));
		bean.addColumn(new SdColumn().setName("servers").setType(SdType.TEXT).setRemark("服务集"));
		bean.addColumn(new SdColumn().setName("external_docs").setType(SdType.TEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName("contact_name").setType(SdType.STRING).setLength(60).setRemark("联系人"));
		bean.addColumn(new SdColumn().setName("contact_info").setType(SdType.TEXT).setRemark("联系人信息"));
		bean.addColumn(new SdColumn().setName("extensions").setType(SdType.TEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName("last_time").setType(SdType.LONG).setRemark("最后操作时间"));
		bean.addColumn(new SdColumn().setName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}

	/**
	 * 获取接口的bean
	 * 
	 * @return
	 */
	public static SdBean getProjectApiBean() {
		SdBean bean = new SdBean();
		bean.setName("project_api").setRemark("API接口表");
		bean.addColumn(
				new SdColumn().setName("api_id").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("API的id"));
		bean.addColumn(
				new SdColumn().setName("group_id").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的id"));
		bean.addColumn(
				new SdColumn().setName("method").setType(SdType.STRING).setLength(10).setNullable(false).setRemark("Method"));
		bean.addColumn(new SdColumn().setName("path").setType(SdType.TEXT).setNullable(false).setRemark("Path"));
		bean.addColumn(new SdColumn().setName("title").setType(SdType.TEXT).setNullable(false).setRemark("接口的名称"));
		bean.addColumn(new SdColumn().setName("description").setType(SdType.TEXT).setRemark("接口的描述"));
		bean.addColumn(new SdColumn().setName("consumes").setType(SdType.TEXT).setRemark("请求类型"));
		bean.addColumn(new SdColumn().setName("parameters").setType(SdType.TEXT).setRemark("请求参数"));
		bean.addColumn(new SdColumn().setName("body").setType(SdType.TEXT).setRemark("请求body"));
		bean.addColumn(new SdColumn().setName("produces").setType(SdType.TEXT).setRemark("响应类型"));
		bean.addColumn(new SdColumn().setName("responses").setType(SdType.TEXT).setRemark("响应参数"));
		bean.addColumn(new SdColumn().setName("deprecated").setType(SdType.STRING).setLength(10).setRemark("接口是否已经过期"));
		bean.addColumn(new SdColumn().setName("additional").setType(SdType.TEXT).setRemark("附加信息"));
		bean.addColumn(new SdColumn().setName("external_docs").setType(SdType.TEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName("extensions").setType(SdType.TEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}

	/**
	 * 获取分组的bean
	 * 
	 * @return
	 */
	public static SdBean getProjectApiGroupBean() {
		SdBean bean = new SdBean();
		bean.setName("project_api_group").setRemark("API接口分组表");
		bean.addColumn(
				new SdColumn().setName("group_id").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("分组的id"));
		bean.addColumn(new SdColumn().setName("project_id").setType(SdType.STRING).setLength(255).setNullable(false)
				.setRemark("项目的id"));
		bean.addColumn(
				new SdColumn().setName("name").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的名称"));
		bean.addColumn(
				new SdColumn().setName("summary").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的简介"));
		bean.addColumn(new SdColumn().setName("description").setType(SdType.TEXT).setRemark("分组的描述"));
		bean.addColumn(new SdColumn().setName("external_docs").setType(SdType.TEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName("extensions").setType(SdType.TEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}

	/**
	 * 获取标签的bean
	 * 
	 * @return
	 */
	public static SdBean getTagsBean() {
		SdBean bean = new SdBean();
		bean.setName("tags").setRemark("标签表");
		bean.addColumn(
				new SdColumn().setName("tid").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("标签的id"));
		bean.addColumn(new SdColumn().setName("tname").setType(SdType.TEXT).setNullable(false).setRemark("标签的名称"));
		bean.addColumn(new SdColumn().setName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		bean.addColumn(new SdColumn().setName("ctime").setType(SdType.LONG).setRemark("创建的时间"));
		return bean;
	}

	/**
	 * 获取用户的bean
	 * 
	 * @return
	 */
	public static SdBean getUsersBean() {
		SdBean bean = new SdBean();
		bean.setName("users").setRemark("用户表");
		bean.addColumn(
				new SdColumn().setName("uid").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("用户的账号"));
		bean.addColumn(
				new SdColumn().setName("role").setType(SdType.STRING).setLength(10).setNullable(false).setRemark("用户的角色"));
		bean.addColumn(new SdColumn().setName("tags").setType(SdType.TEXT).setRemark("用户的标签"));
		bean.addColumn(new SdColumn().setName("pwd").setType(SdType.TEXT).setNullable(false).setRemark("用户的密码"));
		bean.addColumn(
				new SdColumn().setName("nickname").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("用户的昵称"));
		bean.addColumn(new SdColumn().setName("contact").setType(SdType.TEXT).setNullable(false).setRemark("用户的联系信息"));
		bean.addColumn(new SdColumn().setName("summary").setType(SdType.TEXT).setRemark("用户的简介"));
		bean.addColumn(new SdColumn().setName("ctime").setType(SdType.LONG).setRemark("创建的时间"));
		bean.addColumn(new SdColumn().setName("lasttime").setType(SdType.LONG).setRemark("最后访问时间"));
		return bean;
	}

}
