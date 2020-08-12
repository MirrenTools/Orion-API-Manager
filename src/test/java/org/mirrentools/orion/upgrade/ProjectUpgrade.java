package org.mirrentools.orion.upgrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mirrentools.orion.common.ConfigUtil;

/**
 * 用于将旧版本的API升级为最新的数据格式,主要是将schemes,host,base_path,合成为servers集合
 * 
 * @author <a href="http://mirrentools.org">Mirren</a>
 *
 */
public class ProjectUpgrade {
	public static void main(String[] args) throws Exception {
		Connection connection = ConfigUtil.getConnection();
		PreparedStatement pstt = connection.prepareStatement("update project set servers=? where key=?");
		ResultSet query = connection.createStatement().executeQuery("select key,schemes,host,base_path from project ");
		while (query.next()) {
			JSONArray servers = new JSONArray();
			String key = query.getString("key");
			JSONArray schemes = new JSONArray(query.getString("schemes"));
			String host = query.getString("host");
			String basePath = query.getString("base_path");
			for (int i = 0; i < schemes.length(); i++) {
				JSONObject server = new JSONObject();
				String scheme = schemes.getString(i);
				String url = host + (basePath == null ? "" : basePath);
				url = url.replace("http://", "").replace("https://", "").replaceAll("(\\/)+", "/");
				if (url.endsWith("/")) {
					url = url.substring(0, url.length() - 1);
				}
				server.put("url", (scheme.replace("/", "").replace(":", "")) + "://" + url);
				server.put("description", "");
				servers.put(server);
			}
			System.out.println(servers);
			pstt.setString(1, servers.toString());
			pstt.setString(2, key);
			pstt.executeUpdate();
		}

	}
}
