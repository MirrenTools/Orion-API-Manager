
package org.mirrentools.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.mirrentools.orion.common.OpenApiConverter;

import io.swagger.parser.OpenAPIParser;
import io.swagger.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

public class TestConvertSwagger {
	public static void main(String[] args) throws Exception {
		long now = System.currentTimeMillis();
		// 源文件地址
		Path path = Paths.get("C:\\Users\\Mirren\\Downloads\\openapi.json");
		String sb = new String(Files.readAllBytes(path));
		JSONObject convert = OpenApiConverter.convert(sb);
		// 转换后输出文件地址
		Path out = Paths.get("C:\\Users\\Mirren\\Downloads\\my.json");
		Files.deleteIfExists(out);
		Files.write(out, convert.toString().getBytes(), StandardOpenOption.CREATE);
		System.out.println("转换用时(ms): " + (System.currentTimeMillis() - now));

		System.exit(0);

		SwaggerParseResult spr = new OpenAPIParser().readContents(sb, null, null);
		OpenAPI openAPI = spr.getOpenAPI();
		PathItem item = openAPI.getPaths().get("/pet/findByStatus");
		String pretty = Json.pretty(item);
		Matcher matcher = Pattern.compile("(\"\\$ref\" : \")(#/.*)\"+").matcher(pretty);
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(2));
		}
//		System.out.println(pretty);
//		Path out = Paths.get("C:\\Users\\Mirren\\Downloads\\my.json");
//		Files.deleteIfExists(out);
//		Files.write(out, pretty.getBytes(), StandardOpenOption.CREATE);
//		
//		System.out.println(openAPI.toString());
		System.exit(0);
		System.out.println(spr.getMessages());
		System.out.println(openAPI.getInfo().getTitle());
		for (Server s : openAPI.getServers()) {
			System.out.println(s.getUrl());
		}
	}
}