package org.mirrentools.orion.common;

/**
 * 用户的列名
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public interface ColumnsUsers {
	/** 表的名称 */
	public final static String TABLE_NAME = "oam_users";
	/** 用户的id */
	public static final String UID = "oam_uid";
	/** 用户的角色 */
	public static final String ROLE = "oam_role";
	/** 用户的标签 */
	public static final String TAGS = "oam_tags";
	/** 用户的密码 */
	public static final String PWD = "oam_pwd";
	/** 用户的昵称 */
	public static final String NICKNAME = "oam_nickname";
	/** 用户的联系方式 */
	public static final String CONTACT = "oam_contact";
	/** 用户的简介 */
	public static final String SUMMARY = "oam_summary";
	/** 创建的时间 */
	public static final String CTIME = "oam_ctime";
	/** 最后登录时间 */
	public static final String LASTTIME = "oam_lasttime";
}
