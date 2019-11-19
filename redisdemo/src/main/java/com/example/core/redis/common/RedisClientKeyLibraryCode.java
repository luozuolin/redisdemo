package com.example.core.redis.common;

import com.example.core.redis.ehcache.ObtainPropertiesInfo;

/**
 * 保存redis所有key的键值，用来提取所有key然后做批量操作
 * */
public enum RedisClientKeyLibraryCode {
	
	/**
	 * 菜单相关缓存
	 * */
	MENU, MENUBYTE, 
	
	/**
	 * 消息相关缓存
	 * */
	MESSAGE, MESSAGEBYTE, 
	
	/**
	 * 权限相关缓存
	 * */
	AUTH, AUTHBYTE,
	
	/**
	 * SESSION相关
	 * */
	SESSION, SESSIONBYTE,
	
	/**
	 * 通用缓存存储
	 * */
	COMMON, COMMONBYTE,
	
	/**
	 * 锁
	 * */
	LOCK, LOCKBYTE,
	
	/**
	 * 拓展
	 * */
	EXTCODE1, EXTCODE1BYTE,
	EXTCODE2, EXTCODE2BYTE,
	EXTCODE3, EXTCODE3BYTE,
	EXTCODE4, EXTCODE4BYTE,
	EXTCODE5, EXTCODE5BYTE,
	EXTCODE6, EXTCODE6BYTE,
	EXTCODE7, EXTCODE7BYTE,
	EXTCODE8, EXTCODE8BYTE,
	EXTCODE9, EXTCODE9BYTE,
	
	/**
	 * 私有缓存存储
	 * */
	PERSONAL, PERSONALBYTE;
	
	//加系统编号以区分
	private String appCode= ObtainPropertiesInfo.getValByKey("app.code");
	
	public String getAppKeyCode(){
		return new StringBuilder(appCode).append(this.name()).toString();
	}
	
	public String getAppKeyCode(String appCode){
		return new StringBuilder(appCode).append(this.name()).toString();
	}
}