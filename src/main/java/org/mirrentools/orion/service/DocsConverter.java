package org.mirrentools.orion.service;
/**
 * 将其他文档转换为OrionAPI文档的转换器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface DocsConverter {
	/**
	 * 转换为OrionAPI字符串文档
	 * 
	 * @param docs
	 * @return
	 */
	String convert(String docs);
}
