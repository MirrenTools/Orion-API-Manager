package org.mirrentools.orion.common;
/**
 * 项目分享记录的表名属性列
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface ColumnsProjectShare {
	/** 表的名称 */
	public final static String TABLE_NAME = "oam_project_share";
	/** 分享的id */
	public final static String SID = "oam_sid";
	/** 项目的id */
	public final static String PID = "oam_pid";
	/** 查看密码 */
	public final static String PWD = "oam_pwd";
	/** 分享的时间 */
	public final static String SHARE_TIME = "oam_share_time";
}
