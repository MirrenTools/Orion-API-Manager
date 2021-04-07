package org.mirrentools.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

public class TestConvertSwagger {
	public static void main(String[] args) throws Exception {
		Matcher matcher = Pattern.compile("(\\{)(\\w*)(\\})").matcher("https://{username}.gigantic-server.com:{port}/{basePath}");
		while (matcher.find()) {
			String param = matcher.group();
			String key = matcher.group(2);
			System.out.println(matcher.group());
			System.out.println(matcher.group(2));
		}
		System.exit(0);
		Parser parser = Parser.builder().build();
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
		String string = htmlRenderer.render(parser.parse("This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters."));
	
		System.out.println(string.replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
		Path path = Paths.get("C:\\Users\\Mirren\\Downloads\\swagger.json");
		String sb = new String(Files.readAllBytes(path));
		SwaggerParseResult spr = new OpenAPIParser().readContents(sb, null, null);
		OpenAPI openAPI = spr.getOpenAPI();
		System.out.println(spr.getMessages());
		System.out.println(openAPI.getInfo().getTitle());
		for (Server s : openAPI.getServers()) {
			System.out.println(s.getUrl());
		}
	}
}
