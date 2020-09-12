package org.mirrentools.orion.common;
/**
 * 项目的表名属性列
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface ColumnsProject {
	/** 项目的id */
	public final static String KEY = "key";
	/** 项目的名称 */
	public final static String NAME = "name";
	/** 项目的版本号 */
	public final static String VERSIONS = "versions";
	/** 项目的简介 */
	public final static String DESCRIPTION = "description";
	/** 项目的服务集 */
	public final static String SERVERS = "servers";
	/** 联系人 */
	public final static String CONTACT_NAME = "contact_name";
	/** 联系人信息 */
	public final static String CONTACT_INFO = "contact_info";
	/** 附加文档 */
	public final static String EXTERNAL_DOCS = "external_docs";
	/** 拓展属性 */
	public final static String EXTENSIONS = "extensions";
	/** 创建该项目的人 */
	public final static String OWNER = "owner";
	/** 可以查看该项目的人 */
	public final static String OWNERS = "owners";
	/** 最后操作时间 */
	public final static String LAST_TIME = "last_time";
	/** 项目排序 */
	public final static String SORTS = "sorts";
}
