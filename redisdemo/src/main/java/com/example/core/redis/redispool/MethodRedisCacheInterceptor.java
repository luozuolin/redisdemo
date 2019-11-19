package com.example.core.redis.redispool;

import com.example.core.redis.ehcache.SerializeUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.util.SafeEncoder;

public class MethodRedisCacheInterceptor implements MethodInterceptor, InitializingBean {
	private static final Log logger = LogFactory.getLog(MethodRedisCacheInterceptor.class);

	private RedisClientFacade redisClientFacade;

	public MethodRedisCacheInterceptor() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		String hashKeyStr = getCatchHashKey(targetName, methodName, arguments);

		Object result;
			if (redisClientFacade != null) {
			try {
				byte[] byteHashKey = SafeEncoder.encode(targetName);
				byte[] byteKey = SafeEncoder.encode(hashKeyStr);
				byte[] value = redisClientFacade.hget(byteHashKey, byteKey);

				if (null == value || value.length == 0 || null == (result = SerializeUtil.unserialize(value))) {
					result = invocation.proceed();
					byte[] byteValue = SerializeUtil.serialise(result);
					redisClientFacade.hset(byteHashKey, byteKey, byteValue);
				} else {
					logger.info("Found object from redis");
				}
			} catch (Exception e) {
				logger.error(e);
				// 如果 redis 有问题 则不放入 redis 缓存
				result = invocation.proceed();
			}
		} else {
			// 如果 redis 有问题 则不放入 redis 缓存
			result = invocation.proceed();
		}
		return result;
	}

	/**
	 * hashKey 为 方法名
	 */
	private String getCatchHashKey(String targetName, String methodName, Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(":").append(methodName);

		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(":").append(arguments[i]);
			}
		}

		return sb.toString();
	}

	public RedisClientFacade getRedisClientFacade() {
		return redisClientFacade;
	}

	public void setRedisClientFacade(RedisClientFacade redisClientFacade) {
		this.redisClientFacade = redisClientFacade;
	}
}
