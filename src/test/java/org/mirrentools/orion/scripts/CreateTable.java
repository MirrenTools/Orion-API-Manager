package org.mirrentools.orion.scripts;

import org.mirrentools.orion.Constant;
import org.mirrentools.sd.ScrewDriver;
import org.mirrentools.sd.constant.MySQL;
import org.mirrentools.sd.constant.SQLite;
import org.mirrentools.sd.options.ScrewDriverOptions;
import org.mirrentools.sd.options.SdDatabaseOptions;

/**
 * 创建项目需要用到的数据库表,该脚本依赖 <a href="https://github.com/MirrenTools/screw-driver">ScrewDriver</a>
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class CreateTable {
	public static void main(String[] args) {
		// 数据库的驱动类
//		 String driver = SQLite.SQLITE_DERVER;
		 String driver = MySQL.MYSQL_8_DERVER;
		// 数据库的连接地址
//		 String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/config/ConfigDB.db";
		 String url = "jdbc:mysql://127.0.0.1:3306/orion_api_manager?useUnicode=true&useSSL=false&serverTimezone=UTC";
		// 数据库的登录用户
		String username="root"; 
		// 数据库的登录密码
		String password="root"; 
		SdDatabaseOptions databaseOptions = new SdDatabaseOptions(driver, url);
		databaseOptions.setUser(username).setPassword(password);
		
		ScrewDriver screwDriver = ScrewDriver.instance(new ScrewDriverOptions(databaseOptions));
		// 创建项目表
		screwDriver.createTable(Constant.getProjectBean());
		// 创建项目分享记录表
		screwDriver.createTable(Constant.getProjectShareBean());
		// 创建接口分组表
		screwDriver.createTable(Constant.getProjectApiGroupBean());
		// 创建接口表
		screwDriver.createTable(Constant.getProjectApiBean());
		// 创建接口模板表
		screwDriver.createTable(Constant.getProjectApiTemplateBean());
		// 创建用户表
		screwDriver.createTable(Constant.getUsersBean());
		// 创建标签表
		screwDriver.createTable(Constant.getTagsBean());
	}
}
