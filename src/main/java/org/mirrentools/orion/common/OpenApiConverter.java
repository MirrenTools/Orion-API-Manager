package org.mirrentools.orion.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

public class OpenApiConverter {

	public static JSONObject convert(String doc) throws Exception {
		SwaggerParseResult spr = new OpenAPIParser().readContents(doc, null, null);
		OpenAPI openAPI = spr.getOpenAPI();
		if (openAPI == null) {
			throw new Exception(Optional.ofNullable(spr.getMessages()).orElse(new ArrayList<>()).toString());
		}
		Parser mdParser = Parser.builder().build();
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

		JSONObject result = new JSONObject();
		result.put("orionApi", OrionApiManager.VERSION);
		result.put("key", System.currentTimeMillis());
		Info info = Optional.ofNullable(openAPI.getInfo()).orElse(new Info());
		result.put("name", info.getTitle());
		result.put("versions", info.getVersion());
		StringBuilder description = new StringBuilder();
		if (info.getDescription() != null) {
			description.append(htmlRenderer.render(mdParser.parse(info.getDescription())));
		}
		if (info.getTermsOfService() != null) {
			description.append("\n");
			description.append(
					String.format("<div><a href=\"%s\" target=\"_blank\">Terms of service</a></div>", info.getTermsOfService()));
		}
		License license = info.getLicense();
		if (license != null) {
			description.append("\n");
			description.append(String.format("<div><a href=\"%s\" target=\"_blank\">License %s</a></div>",
					Optional.ofNullable(license.getUrl()).orElse("#"), Optional.ofNullable(license.getName()).orElse("")));
		}
		result.put("description", description);
		Contact contact = info.getContact();
		if (contact != null) {
			if (contact.getName() != null) {
				result.put("contactName", contact.getName());
			}
			StringBuilder contactInfo = new StringBuilder();
			if (contact.getEmail() != null) {
				contactInfo
						.append(String.format("Email: <a href=\"mailto:%s\">%s</a> ", contact.getEmail(), contact.getEmail()));
				contactInfo.append("　");
			}
			if (contact.getUrl() != null) {
				contactInfo
						.append(String.format("URL: <a href=\"%s\" target=\"_blank\">%s</a>", contact.getUrl(), contact.getUrl()));
			}
			result.put("contactInfo", contactInfo.toString());
		}
		if (openAPI.getServers() != null) {
			JSONArray servers = new JSONArray();
			for (Server server : openAPI.getServers()) {
				String url = server.getUrl();
				String desc = htmlRenderer.render(mdParser.parse(server.getDescription()));
				if (url.contains("{") && url.contains("}")) {
					ServerVariables variables = server.getVariables() == null ? new ServerVariables() : server.getVariables();
					Matcher matcher = Pattern.compile("(\\{)(\\w*)(\\})").matcher(url);
					while (matcher.find()) {
						String param = matcher.group();
						ServerVariable variable = variables.get(matcher.group(2));
						if (variable == null || variable.getDefault() == null || variable.getDefault().isEmpty()) {
							Server value = new Server();
							value.setUrl(url);
							value.setDescription(desc);
							servers.put(value);
							continue;
						}
						List<String> urls = new ArrayList<>();
						List<String> vals = new ArrayList<>();
						String def = variable.getDefault();
						urls.add(url);
						vals.add(def);
						if (variable.getEnum() != null) {
							for (String e : variable.getEnum()) {
								if (e == null || e.isEmpty() || Objects.equals(def, e)) {
									continue;
								}
								urls.add(url);
								vals.add(e);
							}
						}
						urls.set(0, urls.get(0).replace(param, def));
						for (int i = 1; i < urls.size(); i++) {
							for (int j = 0; j < vals.size(); j++) {
								urls.set(i, urls.get(i).replace(param, vals.get(j)));
							}
						}
					}
					
				} else {
					Server value = new Server();
					value.setUrl(url);
					value.setDescription(desc);
					servers.put(value);
				}
			}
		}

		return null;
	}
}
