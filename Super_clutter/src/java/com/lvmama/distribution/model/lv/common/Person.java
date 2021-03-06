package com.lvmama.distribution.model.lv.common;

import com.lvmama.comm.utils.StringUtil;


public class Person {
	/** 联系人姓名*/
	private String name;
	/** 联系人手机号*/
	private String mobile;
	/** 联系人证件*/
	private String credentials;
	/** 证件类型*/
	private String credentialsType;
	public String getName() {
		return StringUtil.replaceNullStr(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return StringUtil.replaceNullStr(mobile);
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCredentials() {
		return StringUtil.replaceNullStr(credentials);
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getCredentialsType() {
		return StringUtil.replaceNullStr(credentialsType);
	}
	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

}
