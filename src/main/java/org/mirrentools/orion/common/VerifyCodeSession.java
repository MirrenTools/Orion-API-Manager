package org.mirrentools.orion.common;

/**
 * 验证码的回话信息
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class VerifyCodeSession {
	/** 验证码的下标(索引) */
	private String index;
	/** 验证码的值 */
	private String value;
	/** 需要点击的验证码 */
	private String data;
	/** 显示的验证码 */
	private String code;
	/** 最后操作时间 */
	private long lastTime;
	/**
	 * 获取下标
	 * 
	 * @return
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * 设置下标
	 * 
	 * @param index
	 * @return
	 */
	public VerifyCodeSession setIndex(String index) {
		this.index = index;
		return this;
	}
	/**
	 * 获取正确的值
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置正确的值
	 * 
	 * @param value
	 * @return
	 */
	public VerifyCodeSession setValue(String value) {
		this.value = value;
		return this;
	}
	/**
	 * 获取用户要选择的验证码
	 * 
	 * @return
	 */
	public String getData() {
		return data;
	}
	/**
	 * 设置用户需要选中的验证码
	 * 
	 * @param data
	 * @return
	 */
	public VerifyCodeSession setData(String data) {
		this.data = data;
		return this;
	}
	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置验证码
	 * 
	 * @param code
	 * @return
	 */
	public VerifyCodeSession setCode(String code) {
		this.code = code;
		return this;
	}
	/**
	 * 获取最后操作的时间
	 * 
	 * @return
	 */
	public long getLastTime() {
		return lastTime;
	}
	/**
	 * 设置最后操作的时间
	 * 
	 * @param lastTime
	 * @return
	 */
	public VerifyCodeSession setLastTime(long lastTime) {
		this.lastTime = lastTime;
		return this;
	}

	@Override
	public String toString() {
		return "VerifyCodeSession [index=" + index + ", value=" + value + ", data=" + data + ", code=" + code + ", lastTime=" + lastTime + "]";
	}

}
