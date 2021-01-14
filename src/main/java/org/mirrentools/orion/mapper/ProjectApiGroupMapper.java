package org.mirrentools.orion.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.mirrentools.orion.common.ColumnsApiGroup;
import org.mirrentools.orion.common.SqlAssist;
import org.mirrentools.orion.common.SqlAssist.LimitResult;
import org.mirrentools.orion.entity.ProjectApiGroup;

/**
 * ProjectApiGroup数据库相关操作
 * 
 * @author
 */
@Mapper
public interface ProjectApiGroupMapper {

	/**
	 * 获取数据总行数
	 * 
	 * @param assist
	 *          查询帮助类,没有时可以传入null
	 * @return
	 */
	long getCount(SqlAssist assist);

	/**
	 * 查询所有数据
	 * 
	 * @param assist
	 *          查询帮助类,没有时可以传入null
	 * @return
	 */
	List<ProjectApiGroup> selectAll(SqlAssist assist);

	/**
	 * 分页获取数据,
	 * 
	 * @param assist
	 *          查询帮助类,默认page=1,rowSize=15
	 * @return
	 */
	default LimitResult<ProjectApiGroup> limitAll(SqlAssist assist) {
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
		LimitResult<ProjectApiGroup> result = new SqlAssist().new LimitResult<ProjectApiGroup>(count, assist.getPage(), assist.getRowSize());
		if (count == 0) {
			return result;
		}
		List<ProjectApiGroup> data = selectAll(assist);
		result.setData(data);
		return result;
	}

	/**
	 * 通过对象中不为空的属性为条件获取数据,只返回一条结果
	 * 
	 * @param data
	 * @return
	 */
	ProjectApiGroup selectSingleByObj(ProjectApiGroup data);

	/**
	 * 通过对象中不为空的属性作为条件获取数据
	 * 
	 * @param data
	 * @return
	 */
	List<ProjectApiGroup> selectByObj(ProjectApiGroup data);

	/**
	 * 插入一个对象包括属性值为null的值
	 * 
	 * @param data
	 * @return
	 */
	int insertAll(ProjectApiGroup data);

	/**
	 * 插入一个对象,只插入对象中值不为null的属性
	 * 
	 * @param data
	 * @return
	 */
	int insertNotNull(ProjectApiGroup data);

	/**
	 * 批量添加全部所有字段
	 * 
	 * @param data
	 * @return
	 */
	int insertBatch(List<ProjectApiGroup> data);

	/**
	 * 插入一个对象,如果该对象不存在就新建如果该对象已经存在就更新
	 * 
	 * @param data
	 * @return
	 */
	int replace(ProjectApiGroup data);

	/**
	 * 通过查询帮助类删除数据
	 * 
	 * @param assist
	 *          查询帮助类,没有时可以传入null
	 * @return
	 */
	int deleteByAssist(SqlAssist assist);

	/**
	 * 更新一个对象中所有的属性包括null值,条件为SqlAssist查询帮助类
	 * 
	 * @param data
	 *          对象
	 * @param assist
	 *          查询帮助类
	 * @return
	 */
	int updateAllByAssist(@Param("data") ProjectApiGroup data, @Param("assist") SqlAssist assist);

	/**
	 * 更新一个对象中属性不为null值,条件为SqlAssist查询帮助类
	 * 
	 * @param data
	 *          对象
	 * @param assist
	 *          查询帮助类
	 * @return
	 */
	int updateNotNullByAssist(@Param("data") ProjectApiGroup data, @Param("assist") SqlAssist assist);
	/**
	 * 通过id获取数据
	 * 
	 * @param id
	 * @return
	 */
	ProjectApiGroup selectById(String id);
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
	int updateAllById(ProjectApiGroup data);

	/**
	 * 更新一个对象中属性不为null值,条件为对象中的id
	 * 
	 * @param data
	 * @return
	 */
	int updateNotNullById(ProjectApiGroup data);
	/**
	 * 将接口分组上移动
	 * 
	 * @param gid
	 *          分组的id
	 * @return
	 */
	@Update("UPDATE " + ColumnsApiGroup.TABLE_NAME 
				+ " SET " + ColumnsApiGroup.SORTS + "=" + ColumnsApiGroup.SORTS + "-1 "
				+ " WHERE "+ ColumnsApiGroup.GROUP_ID + "=#{gid}")
	int updateProjectApiGroupMoveUp(@Param("gid") String gid);

	/**
	 * 将接口分组下移动
	 * 
	 * @param gid
	 *          分组的id
	 * @return
	 */
	@Update("UPDATE " + ColumnsApiGroup.TABLE_NAME 
			+ " SET " + ColumnsApiGroup.SORTS + "=" + ColumnsApiGroup.SORTS + "+1 "
			+ " WHERE "+ ColumnsApiGroup.GROUP_ID + "=#{gid}")
	int updateProjectApiGroupMoveDown(@Param("gid") String gid);
}
