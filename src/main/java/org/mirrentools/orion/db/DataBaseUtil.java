package org.mirrentools.orion.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.mirrentools.orion.common.FunctionResult;
import org.mirrentools.orion.common.UpdateResult;

/**
 * 数据库工具,默认为SQLite数据库,如果需要改成其他数据库重写getConnection就可以了
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class DataBaseUtil {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/config/ConfigDB.db";

	/**
	 * 获得数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL);
		return conn;
	}

	/**
	 * 执行查询
	 * 
	 * @param sql     SQL语句
	 * @param handler 返回结果
	 */
	public static <R> FunctionResult<R> query(String sql, Function<ResultSet, FunctionResult<R>> handler)
			throws Exception {
		try (Connection conn = getConnection()) {
			try (Statement stat = conn.createStatement()) {
				try (ResultSet set = stat.executeQuery(sql)) {
					return handler.apply(set);
				}
			}
		}
	}

	/**
	 * 执行查询
	 * 
	 * @param sql    SQL语句
	 * @param params 参数 不能为null
	 * @return
	 * @throws Exception
	 */
	public static <R> FunctionResult<R> query(String sql, List<Object> params,
			Function<ResultSet, FunctionResult<R>> handler) throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				try (ResultSet set = stat.executeQuery()) {
					return handler.apply(set);
				}
			}
		}
	}

	/**
	 * 执行更新操作
	 * 
	 * @param sql SQL语句
	 * @return
	 * @throws Exception
	 */
	public static int update(String sql) throws Exception {
		try (Connection conn = getConnection()) {
			try (Statement stat = conn.createStatement()) {
				return stat.executeUpdate(sql);
			}
		}
	}

	/**
	 * 执行更新操作
	 * 
	 * @param sql    SQL语句
	 * @param params 参数,不能为null
	 * @return
	 * @throws Exception
	 */
	public static int update(String sql, List<Object> params) throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				return stat.executeUpdate();
			}
		}
	}

	/**
	 * 执行更新并返回主键
	 * 
	 * @param sql    SQL语句
	 * @param params 参数,不能为null
	 * @return
	 * @throws Exception
	 */
	public static UpdateResult updateGeneratedKeys(String sql, List<Object> params) throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				int executeUpdate = stat.executeUpdate();
				try (ResultSet set = stat.getGeneratedKeys()) {
					List<Object> keys = new ArrayList<>();
					while (set.next()) {
						keys.add(set.getObject(1));
					}
					return new UpdateResult(executeUpdate, keys);
				}
			}
		}
	}

}
