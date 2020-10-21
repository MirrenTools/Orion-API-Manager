package org.mirrentools.orion.scripts;

import java.util.HashMap;
import java.util.Map;

import org.mirrentools.orion.Constant;
import org.mirrentools.sd.ScrewDriver;
import org.mirrentools.sd.models.SdTemplate;
import org.mirrentools.sd.options.ScrewDriverOptions;

/**
 * 创建代码
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class CreateCode {
	/**
	 * 不同数据库只需要修改这个变量后重新支持生成
	 * MySQL = MySqlMapper.ftl
	 * PostgreSQL = PostgreSqlMapper.ftl
	 * SQLite = SqliteMapper.ftl
	 * Oracle = OracleMapper.ftl
	 * SQL Server = SqlServerMapper.ftl
	 * DB2 = DB2Mapper.ftl
	 */
	private static String MYBATIS_MAAPPER_TEMPLATE = "MySqlMapper.ftl";
	public static void main(String[] args) {
		ScrewDriver screwDriver = ScrewDriver.instance(new ScrewDriverOptions());

		// 创建项目的代码
		screwDriver.createCode(Constant.getProjectBean(), getTemplates("Project"));
		// 创建接口分组的代码
		screwDriver.createCode(Constant.getProjectApiGroupBean(), getTemplates("ProjectApiGroup"));
		// 创建接口的代码
		screwDriver.createCode(Constant.getProjectApiBean(), getTemplates("ProjectApi"));
		// 创建接口模板的代码
		screwDriver.createCode(Constant.getProjectApiTemplateBean(), getTemplates("ProjectApiTemplate"));
		// 创建用户代码
		screwDriver.createCode(Constant.getUsersBean(), getTemplates("Users"));
		// 创建用户代码
		screwDriver.createCode(Constant.getTagsBean(), getTemplates("Tags"));
	}

	/**
	 * 获取生成代码需要用到的模板
	 * 
	 * @param entityName
	 *          类的名称
	 * @return
	 */
	public static Map<String, SdTemplate> getTemplates(String entityName) {
		String packageName = "org.mirrentools.orion.";
		String templatePath = "mybatis";
		// 设置实体生成模板
		Map<String, SdTemplate> templates = new HashMap<String, SdTemplate>();
		// 实体类
		templates.put("entity", new SdTemplate()
				.setPath(templatePath)
				.setFile("Entity.ftl")
				.setPackageName(packageName + "entity")
				.setOverride(false).setClassName(entityName));
		// 数据库操作dao
		templates.put("dao", new SdTemplate()
				.setPath(templatePath)
				.setFile("Dao.ftl")
				.setPackageName(packageName + "mapper").setOverride(false)
				.setClassName(entityName + "Mapper"));
		// 查询帮助类
		templates.put("assist", new SdTemplate()
				.setPath(templatePath)
				.setFile("SqlAssist.ftl")
				.setPackageName(packageName + "common")
				.setOverride(false).setClassName("SqlAssist"));
		// MyBatis的mapper
		templates.put("mapper", new SdTemplate()
				.setPath(templatePath)
				.setFile(MYBATIS_MAAPPER_TEMPLATE)
				.setPackageName("mappers")
				.setSourceFolder("src/main/resources")
				.setClassName(entityName + "Mapper").setSuffix(".xml"));
		return templates;
	}
}
