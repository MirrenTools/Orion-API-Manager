package org.mirrentools.orion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mirrentools.orion.common.ColumnsAPI;
import org.mirrentools.orion.common.ColumnsApiGroup;
import org.mirrentools.orion.common.ColumnsProject;
import org.mirrentools.orion.common.SqlAssist;
import org.mirrentools.orion.common.SqlAssist.LimitResult;
import org.mirrentools.orion.entity.Project;

/**
 * Project数据库相关操作
 * 
 * @author
 */
@Mapper
public interface ProjectMapper {

	/**
	 * 获取数据总行数
	 * 
	 * @param assist 查询帮助类,没有时可以传入null
	 * @return
	 */
	long getCount(SqlAssist assist);

	/**
	 * 查询所有数据
	 * 
	 * @param assist 查询帮助类,没有时可以传入null
	 * @return
	 */
	List<Project> selectAll(SqlAssist assist);

	/**
	 * 分页获取数据,
	 * 
	 * @param assist 查询帮助类,默认page=1,rowSize=15
	 * @return
	 */
	default LimitResult<Project> limitAll(SqlAssist assist) {
		if (assist == null) {
			assist = new SqlAssist();
		}
		if (assist.getPage() == null || assist.getPage() < 1) {
			assist.setPage(1);
		}
		if (assist.getRowSize() == null || assist.getRowSize() < 1) {
			assist.setRowSize(15);
		}
		if (assist.getPage() == 1) {
			assist.setStartRow(0);
		} else {
			assist.setStartRow((assist.getPage() - 1) * assist.getRowSize());
		}
		long count = getCount(assist);
		LimitResult<Project> result = new SqlAssist().new LimitResult<Project>(count, assist.getPage(),
				assist.getRowSize());
		if (count == 0) {
			return result;
		}
		List<Project> data = selectAll(assist);
		result.setData(data);
		return result;
	}

	/**
	 * 通过对象中不为空的属性为条件获取数据,只返回一条结果
	 * 
	 * @param data
	 * @return
	 */
	Project selectSingleByObj(Project data);

	/**
	 * 通过对象中不为空的属性作为条件获取数据
	 * 
	 * @param data
	 * @return
	 */
	List<Project> selectByObj(Project data);

	/**
	 * 插入一个对象包括属性值为null的值
	 * 
	 * @param data
	 * @return
	 */
	int insertAll(Project data);

	/**
	 * 插入一个对象,只插入对象中值不为null的属性
	 * 
	 * @param data
	 * @return
	 */
	int insertNotNull(Project data);

	/**
	 * 批量添加全部所有字段
	 * 
	 * @param data
	 * @return
	 */
	int insertBatch(List<Project> data);

	/**
	 * 插入一个对象,如果该对象不存在就新建如果该对象已经存在就更新
	 * 
	 * @param data
	 * @return
	 */
	int replace(Project data);

	/**
	 * 通过查询帮助类删除数据
	 * 
	 * @param assist 查询帮助类,没有时可以传入null
	 * @return
	 */
	int deleteByAssist(SqlAssist assist);

	/**
	 * 更新一个对象中所有的属性包括null值,条件为SqlAssist查询帮助类
	 * 
	 * @param data   对象
	 * @param assist 查询帮助类
	 * @return
	 */
	int updateAllByAssist(@Param("data") Project data, @Param("assist") SqlAssist assist);

	/**
	 * 更新一个对象中属性不为null值,条件为SqlAssist查询帮助类
	 * 
	 * @param data   对象
	 * @param assist 查询帮助类
	 * @return
	 */
	int updateNotNullByAssist(@Param("data") Project data, @Param("assist") SqlAssist assist);

	/**
	 * 通过id获取数据
	 * 
	 * @param id
	 * @return
	 */
	Project selectById(String id);

	/**
	 * 通过id删除数据
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(String id);

	/**
	 * 更新一个对象中所有的属性包括null值,条件为对象中的id
	 * 
	 * @param data
	 * @return
	 */
	int updateAllById(Project data);

	/**
	 * 更新一个对象中属性不为null值,条件为对象中的id
	 * 
	 * @param data
	 * @return
	 */
	int updateNotNullById(Project data);

	/**
	 * 通过api的id获取项目的负责人uid
	 * 
	 * @param apiId
	 * @return
	 */
	@Select(" select p.* from project p" 
			+ " inner join project_api_group g on p." + ColumnsProject.KEY + "=g."+ ColumnsApiGroup.PROJECT_ID 
			+ " inner join project_api a on a." + ColumnsAPI.GROUP_ID + "=g."+ ColumnsApiGroup.GROUP_ID
			+ " where a."+ColumnsAPI.API_ID+"=#{apiId}")
	Project getProjectOwnerByApiId(@Param("apiId") String apiId);

	/**
	 * 将项目上移动
	 * 
	 * @param pid 项目的id
	 * @return
	 */
	@Update("UPDATE project SET "+ColumnsProject.SORTS+"="+ColumnsProject.SORTS+"-1 WHERE key=#{pid}")
	int updateProjectMoveUp(@Param("pid") String pid);

	/**
	 * 将项目下移动
	 * 
	 * @param pid 项目的id
	 * @return
	 */
	@Update("UPDATE project SET "+ColumnsProject.SORTS+"="+ColumnsProject.SORTS+"+1 WHERE key=#{pid}")
	int updateProjectMoveDown(@Param("pid") String pid);
}
