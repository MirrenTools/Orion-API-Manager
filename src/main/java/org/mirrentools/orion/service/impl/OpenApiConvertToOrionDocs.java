package org.mirrentools.orion.service.impl;

import org.json.JSONObject;
import org.mirrentools.orion.common.OpenApiConverter;
import org.mirrentools.orion.service.DocsConverter;

/**
 * OpenAPI文档转换为OrionAPI文档
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class OpenApiConvertToOrionDocs implements DocsConverter {
	@Override
	public String convert(String docs) {
		try {
			JSONObject convert = OpenApiConverter.convert(docs);
			return convert.toString(2);
		} catch (Exception e) {
			e.printStackTrace();
			return e == null ? "convert openapi failed" : e.getMessage();
		}
	}

}
