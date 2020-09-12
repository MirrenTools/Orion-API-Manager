package org.mirrentools.orion.scripts;

import org.mirrentools.orion.Constant;
import org.mirrentools.sd.ScrewDriver;
import org.mirrentools.sd.options.ScrewDriverOptions;
import org.mirrentools.sd.options.SdDatabaseOptions;

/**
 * 创建项目需要用到的数据库表
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class CreateTable {
	public static void main(String[] args) {
		// 数据库的驱动
		String driver = "org.sqlite.JDBC";
		// 数据库的连接地址
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/config/ConfigDB.db";
		
		SdDatabaseOptions databaseOptions = new SdDatabaseOptions(driver, url);

		ScrewDriver screwDriver = ScrewDriver.instance(new ScrewDriverOptions(databaseOptions));
		// 创建项目表
		screwDriver.createTable(Constant.getProjectBean());
		// 创建接口分组表
		screwDriver.createTable(Constant.getProjectApiGroupBean());
		// 创建接口表
		screwDriver.createTable(Constant.getProjectApiBean());
		// 创建用户表
		screwDriver.createTable(Constant.getUsersBean());
		// 创建标签表
		screwDriver.createTable(Constant.getTagsBean());
	}
}
