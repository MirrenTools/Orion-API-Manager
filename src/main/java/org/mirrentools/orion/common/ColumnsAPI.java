package org.mirrentools.orion.common;

/**
 * 接口表的列名
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface ColumnsAPI {
	/** 表的名称 */
	public final static String TABLE_NAME = "oam_project_api";
	/** API的id */
	public static final String API_ID = "oam_api_id";
	/** 分组的id */
	public static final String GROUP_ID = "oam_group_id";
	/** 请求方法 */
	public static final String METHOD = "oam_method";
	/** 请求的path */
	public static final String PATH = "oam_path";
	/** API的名称 */
	public static final String TITLE = "oam_title";
	/** API的简介 */
	public static final String DESCRIPTION = "oam_description";
	/** 请求类型 */
	public static final String CONSUMES = "oam_consumes";
	/** 请求参数 */
	public static final String PARAMETERS = "oam_parameters";
	/** 请求Body */
	public static final String BODY = "oam_body";
	/** 响应类型 */
	public static final String PRODUCES = "oam_produces";
	/** 响应参数 */
	public static final String RESPONSES = "oam_responses";
	/** API维护中 */
	public static final String DEPRECATED = "oam_deprecated";
	/** 附近参数 */
	public static final String ADDITIONAL = "oam_additional";
	/** 拓展文档 */
	public static final String EXTERNAL_DOCS = "oam_external_docs";
	/** 附加参数 */
	public static final String EXTENSIONS = "oam_extensions";
	/** 排序 */
	public static final String SORTS = "oam_sorts";
}
