package org.mirrentools.orion.common;
/**
 * 没有下一步追踪信息的异常
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class NoStackTraceThrowable extends Throwable {
	private static final long serialVersionUID = 631462353705590765L;
	public NoStackTraceThrowable(String message) {
		super(message, null, false, false);
	}
}
