package org.mirrentools.orion;

import org.mirrentools.orion.common.ColumnsAPI;
import org.mirrentools.orion.common.ColumnsApiGroup;
import org.mirrentools.orion.common.ColumnsApiTemplate;
import org.mirrentools.orion.common.ColumnsProject;
import org.mirrentools.orion.common.ColumnsProjectShare;
import org.mirrentools.orion.common.ColumnsTags;
import org.mirrentools.orion.common.ColumnsUsers;
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
		bean.setName(ColumnsProject.TABLE_NAME).setAlias("project").setRemark("项目表");
		bean.addColumn(new SdColumn().setName(ColumnsProject.KEY).setFieldName("key").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("项目的id"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.NAME).setFieldName("name").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("名称"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.VERSIONS).setFieldName("versions").setType(SdType.STRING).setLength(60).setRemark("版本号"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.DESCRIPTION).setFieldName("description").setType(SdType.TEXT).setRemark("描述"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.SERVERS).setFieldName("servers").setType(SdType.TEXT).setRemark("服务集"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.EXTERNAL_DOCS).setFieldName("externalDocs").setType(SdType.TEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.CONTACT_NAME).setFieldName("contactName").setType(SdType.STRING).setLength(60).setRemark("联系人"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.CONTACT_INFO).setFieldName("contactInfo").setType(SdType.TEXT).setRemark("联系人信息"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.EXTENSIONS).setFieldName("extensions").setType(SdType.TEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.OWNER).setFieldName("owner").setType(SdType.STRING).setLength(255).setRemark("创建该项目的人"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.OWNERS).setFieldName("owners").setType(SdType.TEXT).setRemark("可以查看该项目的人"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.LAST_TIME).setFieldName("lastTime").setType(SdType.LONG).setRemark("最后操作时间"));
		bean.addColumn(new SdColumn().setName(ColumnsProject.SORTS).setFieldName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}
	/**
	 * 获取项目分享的Bean
	 * 
	 * @return
	 */
	public static SdBean getProjectShareBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsProjectShare.TABLE_NAME).setAlias("project_share").setRemark("项目记录表");
		bean.addColumn(new SdColumn().setName(ColumnsProjectShare.SID).setFieldName("sid").setType(SdType.STRING).setLength(32).setPrimary(true).setRemark("分享的id"));
		bean.addColumn(new SdColumn().setName(ColumnsProjectShare.PID).setFieldName("pid").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("项目的id"));
		bean.addColumn(new SdColumn().setName(ColumnsProjectShare.PWD).setFieldName("pwd").setType(SdType.STRING).setLength(32).setNullable(false).setRemark("查看密码"));
		bean.addColumn(new SdColumn().setName(ColumnsProjectShare.SHARE_TIME).setFieldName("shareTime").setType(SdType.LONG).setRemark("分享的时间"));
		return bean;
	}

	/**
	 * 获取接口的bean
	 * 
	 * @return
	 */
	public static SdBean getProjectApiBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsAPI.TABLE_NAME).setAlias("project_api").setRemark("API接口表");
		bean.addColumn(new SdColumn().setName(ColumnsAPI.API_ID).setFieldName("apiId").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("API的id"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.GROUP_ID).setFieldName("groupId").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的id"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.METHOD).setFieldName("method").setType(SdType.STRING).setLength(10).setNullable(false).setRemark("Method"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.PATH).setFieldName("path").setType(SdType.TEXT).setNullable(false).setRemark("Path"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.TITLE).setFieldName("title").setType(SdType.TEXT).setNullable(false).setRemark("接口的名称"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.DESCRIPTION).setFieldName("description").setType(SdType.TEXT).setRemark("接口的描述"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.CONSUMES).setFieldName("consumes").setType(SdType.TEXT).setRemark("请求类型"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.PARAMETERS).setFieldName("parameters").setType(SdType.TEXT).setRemark("请求参数"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.BODY).setFieldName("body").setType(SdType.TEXT).setRemark("请求body"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.PRODUCES).setFieldName("produces").setType(SdType.TEXT).setRemark("响应类型"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.RESPONSES).setFieldName("responses").setType(SdType.TEXT).setRemark("响应参数"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.DEPRECATED).setFieldName("deprecated").setType(SdType.STRING).setLength(10).setRemark("接口是否已经过期"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.ADDITIONAL).setFieldName("additional").setType(SdType.TEXT).setRemark("附加信息"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.EXTERNAL_DOCS).setFieldName("externalDocs").setType(SdType.TEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.EXTENSIONS).setFieldName("extensions").setType(SdType.TEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName(ColumnsAPI.SORTS).setFieldName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}

	/**
	 * 获取API模板的bean
	 * 
	 * @return
	 */
	public static SdBean getProjectApiTemplateBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsApiTemplate.TABLE_NAME).setAlias("project_api_template").setRemark("API接口模板");
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.TID).setFieldName("tid").setType(SdType.STRING).setLength(32).setPrimary(true).setRemark("模板的id"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.UID).setFieldName("uid").setType(SdType.STRING).setLength(250).setRemark("模板所属用户的id"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.NAME).setFieldName("name").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("模板的名称"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.API).setFieldName("api").setType(SdType.NTEXT).setRemark("API基本信息"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.PARAMETERS).setFieldName("parameters").setType(SdType.NTEXT).setRemark("API中的请求参数"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.RESPONSES).setFieldName("responses").setType(SdType.NTEXT).setRemark("API中的响应参数"));
		bean.addColumn(new SdColumn().setName(ColumnsApiTemplate.CTIME).setFieldName("ctime").setType(SdType.LONG).setRemark("模板创建的时间"));
		return bean;
	}

	/**
	 * 获取分组的bean
	 * 
	 * @return
	 */
	public static SdBean getProjectApiGroupBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsApiGroup.TABLE_NAME).setAlias("project_api_group").setRemark("API接口分组表");
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.GROUP_ID).setFieldName("groupId").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("分组的id"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.PROJECT_ID).setFieldName("projectId").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("项目的id"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.NAME).setFieldName("name").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的名称"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.SUMMARY).setFieldName("summary").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("分组的简介"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.DESCRIPTION).setFieldName("description").setType(SdType.NTEXT).setRemark("分组的描述"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.EXTERNAL_DOCS).setFieldName("externalDocs").setType(SdType.NTEXT).setRemark("附加文档"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.EXTENSIONS).setFieldName("extensions").setType(SdType.NTEXT).setRemark("拓展信息"));
		bean.addColumn(new SdColumn().setName(ColumnsApiGroup.SORTS).setFieldName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		return bean;
	}

	/**
	 * 获取标签的bean
	 * 
	 * @return
	 */
	public static SdBean getTagsBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsTags.TABLE_NAME).setAlias("tags").setRemark("标签表");
		bean.addColumn(new SdColumn().setName(ColumnsTags.TID).setFieldName("tid").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("标签的id"));
		bean.addColumn(new SdColumn().setName(ColumnsTags.TNAME).setFieldName("tname").setType(SdType.NTEXT).setNullable(false).setRemark("标签的名称"));
		bean.addColumn(new SdColumn().setName(ColumnsTags.SORTS).setFieldName("sorts").setType(SdType.INTEGER).setDefaults("0").setRemark("项目排序"));
		bean.addColumn(new SdColumn().setName(ColumnsTags.CTIME).setFieldName("ctime").setType(SdType.LONG).setRemark("创建的时间"));
		return bean;
	}

	/**
	 * 获取用户的bean
	 * 
	 * @return
	 */
	public static SdBean getUsersBean() {
		SdBean bean = new SdBean();
		bean.setName(ColumnsUsers.TABLE_NAME).setAlias("users").setRemark("用户表");
		bean.addColumn(new SdColumn().setName(ColumnsUsers.UID).setFieldName("uid").setType(SdType.STRING).setLength(255).setPrimary(true).setRemark("用户的账号"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.ROLE).setFieldName("role").setType(SdType.STRING).setLength(10).setNullable(false).setRemark("用户的角色"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.TAGS).setFieldName("tags").setType(SdType.NTEXT).setRemark("用户的标签"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.PWD).setFieldName("pwd").setType(SdType.NTEXT).setNullable(false).setRemark("用户的密码"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.NICKNAME).setFieldName("nickname").setType(SdType.STRING).setLength(255).setNullable(false).setRemark("用户的昵称"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.CONTACT).setFieldName("contact").setType(SdType.NTEXT).setNullable(false).setRemark("用户的联系信息"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.SUMMARY).setFieldName("summary").setType(SdType.NTEXT).setRemark("用户的简介"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.CTIME).setFieldName("ctime").setType(SdType.LONG).setRemark("创建的时间"));
		bean.addColumn(new SdColumn().setName(ColumnsUsers.LASTTIME).setFieldName("lasttime").setType(SdType.LONG).setRemark("最后访问时间"));
		return bean;
	}

}
