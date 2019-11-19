package com.example.core.redis.redispool;

import com.example.core.redis.ehcache.ObtainPropertiesInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.util.SafeEncoder;

import java.lang.reflect.Method;


public class MethodRedisCacheAfterAdvice implements AfterReturningAdvice,InitializingBean {
	private static final Log logger = LogFactory.getLog(MethodRedisCacheAfterAdvice.class);

	private RedisClientFacade redisClientFacade;
	
	@Override
	public void afterPropertiesSet() throws Exception {}

	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {

		String className = target.getClass().getName();
		if(redisClientFacade != null){//如果 redis 有问题 则不放入 redis 缓存
			byte[] byteHashKey= SafeEncoder.encode(className);
			redisClientFacade.del(byteHashKey);
			logger.info("remove redis cache "+ ObtainPropertiesInfo.getValByKey("app.code")+className);
		}
	}

	public RedisClientFacade getRedisClientFacade() {
		return redisClientFacade;
	}

	public void setRedisClientFacade(RedisClientFacade redisClientFacade) {
		this.redisClientFacade = redisClientFacade;
	}
}