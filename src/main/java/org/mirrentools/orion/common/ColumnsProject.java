package org.mirrentools.orion.common;
/**
 * 项目的表名属性列
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface ColumnsProject {
	/** 表的名称 */
	public final static String TABLE_NAME = "oam_project";
	
	/** 项目的id */
	public final static String KEY = "oam_key";
	/** 项目的名称 */
	public final static String NAME = "oam_name";
	/** 项目的版本号 */
	public final static String VERSIONS = "oam_versions";
	/** 项目的简介 */
	public final static String DESCRIPTION = "oam_description";
	/** 项目的服务集 */
	public final static String SERVERS = "oam_servers";
	/** 联系人 */
	public final static String CONTACT_NAME = "oam_contact_name";
	/** 联系人信息 */
	public final static String CONTACT_INFO = "oam_contact_info";
	/** 附加文档 */
	public final static String EXTERNAL_DOCS = "oam_external_docs";
	/** 拓展属性 */
	public final static String EXTENSIONS = "oam_extensions";
	/** 创建该项目的人 */
	public final static String OWNER = "oam_owner";
	/** 可以查看该项目的人 */
	public final static String OWNERS = "oam_owners";
	/** 最后操作时间 */
	public final static String LAST_TIME = "oam_last_time";
	/** 项目排序 */
	public final static String SORTS = "oam_sorts";
}
