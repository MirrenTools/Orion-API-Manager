package org.mirrentools.orion.common;

import java.util.List;

/**
 * 返回结果
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class UpdateResult {
	private int updated;
	private List<Object> keys;
	public UpdateResult(int updated, List<Object> keys) {
		super();
		this.updated = updated;
		this.keys = keys;
	}
	public int getUpdated() {
		return updated;
	}
	public List<Object> getGeneratedKeys() {
		return keys;
	}

}
