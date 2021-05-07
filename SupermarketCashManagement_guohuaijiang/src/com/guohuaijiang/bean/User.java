package com.guohuaijiang.bean;
/**
 *
 * @开发者 郭怀江
 * @类别
 * @开始日期
 * @结束日期
 * @版本
 * @说明
 */
public class User {
	/**
	 * 用户名
	 */
	private String user_name;
	/**---
	 * 用户密码
	 */
	private String user_pwd;
	/**
	 * 用户身份
	 */
	private String user_type;
	/**
	 * 用户编号
	 */
	private String user_no;

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
}
