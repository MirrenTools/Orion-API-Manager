package org.mirrentools.orion.common;
/**
 * 项目分享记录的表名属性列
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */

public interface ColumnsApiTemplate {
	/** 表的名称 */
	public final static String TABLE_NAME = "oam_project_api_template";
	/** 模板的id */
	public final static String TID = "oam_tid";
	/** 模板所属用户的id */
	public final static String UID = "oam_uid";
	/** 模板的名称 */
	public final static String NAME = "oam_name";
	/** API基本信息 */
	public final static String API = "oam_api";
	/** API中的请求参数 */
	public final static String PARAMETERS = "oam_parameters";
	/** API中的响应参数 */
	public final static String RESPONSES = "oam_responses";
	/** 模板创建的时间 */
	public final static String CTIME = "oam_ctime";
}
