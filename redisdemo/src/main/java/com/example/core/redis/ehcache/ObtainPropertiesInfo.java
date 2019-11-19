package com.example.core.redis.ehcache;

import java.util.ResourceBundle;
/**
 * 获取 *.properties配置文件内容
 *
 */
public class ObtainPropertiesInfo {
	/**
	 * 
	 */
	private static ResourceBundle ssoBundle = ResourceBundle.getBundle("application");
	/**
	 * 通過 key 获取value值
	 * @param key
	 * @return
	 */
    public static String getValByKey(String key) {
        String value = null;
        if(key != null && !"".equals(key.trim())) {
            value = ssoBundle.getString(key.trim());
        }
        return value;
    }
}
