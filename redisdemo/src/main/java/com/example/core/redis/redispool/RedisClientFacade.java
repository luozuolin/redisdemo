package com.example.core.redis.redispool;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.core.redis.common.RedisClientKeyLibraryCode;
import com.example.core.redis.ehcache.ObtainPropertiesInfo;
import redis.clients.util.SafeEncoder;

/**
 * redisClient封装类
 * */
public class RedisClientFacade {

	private String EMPTY_STRING_VALUE= "";
	private byte[] EMPTY_BYTE_VALUE= {};

	private static final String appCode = ObtainPropertiesInfo.getValByKey("app.code");
	private static final String allkeyreg = "ALL_KEY_REG_"+appCode;
	private static final String allhashkeyreg = "ALL_HASHKEY_REG_"+appCode;
	private static final String personal_hashkey = "PERSONAL_HASHKEY_"+appCode;


    private RedisClient redisClient;
    private RedisClient redisClient2;
    private String namespace;

    
    public Object eval(String script, List<String> keys, List<String> args){
    	return redisClient2.eval(script, keys, args);
    }

    //公用缓存数据
    public Long set(RedisClientKeyLibraryCode keyLibrary, String key, String value) throws Exception {
    	if(!redisClient.exists(this.namespace, keyLibrary.getAppKeyCode()+key)){
    		redisClient.hset(this.namespace, allkeyreg,keyLibrary.getAppKeyCode()+key,EMPTY_STRING_VALUE);
    	}
    	
    	return redisClient.hset(this.namespace, keyLibrary.getAppKeyCode(),key,value);
    }

    public Long set(RedisClientKeyLibraryCode keyLibrary, byte[] key, byte[] value) throws Exception {
    	String keyStr=keyLibrary.getAppKeyCode()+SafeEncoder.encode(key);
    	if(!redisClient.exists(this.namespace, SafeEncoder.encode(keyStr))){
    		redisClient.hset(this.namespace, SafeEncoder.encode(allkeyreg),SafeEncoder.encode(keyStr),EMPTY_BYTE_VALUE);
    	}
    	return redisClient.hset(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key,value);
    }

    public String get(RedisClientKeyLibraryCode keyLibrary ,String key) throws Exception {
    	if(redisClient.exists(this.namespace,keyLibrary.getAppKeyCode()+key)){
    		return redisClient.hget(this.namespace, keyLibrary.getAppKeyCode(),key);
    	}else{
    		if(redisClient.hexists(this.namespace, allkeyreg, keyLibrary.getAppKeyCode()+key)){
    			return redisClient.hget(this.namespace, keyLibrary.getAppKeyCode(),key);
    		}
    		redisClient.hdel(this.namespace, keyLibrary.getAppKeyCode(),key);
    		return null;
    	}
    }
    
    public String get(RedisClientKeyLibraryCode keyLibrary ,String key,String namespace,String appCode) throws Exception {
    	if(redisClient.exists(namespace,keyLibrary.getAppKeyCode(appCode)+key)){
    		return redisClient.hget(namespace, keyLibrary.getAppKeyCode(appCode),key);
    	}else{
    		if(redisClient.hexists(namespace, "ALL_KEY_REG_" + appCode, keyLibrary.getAppKeyCode(appCode)+key)){
    			return redisClient.hget(namespace, keyLibrary.getAppKeyCode(appCode),key);
    		}
    		redisClient.hdel(namespace, keyLibrary.getAppKeyCode(appCode),key);
    		return null;
    	}
    }

    public byte[] get(RedisClientKeyLibraryCode keyLibrary,byte[] key) throws Exception {
    	String keyStr=keyLibrary.getAppKeyCode()+SafeEncoder.encode(key);
    	if(redisClient.exists(this.namespace,SafeEncoder.encode(keyStr))){
    		return redisClient.hget(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key);
    	}else{
    		if(redisClient.hexists(this.namespace, SafeEncoder.encode(allkeyreg), SafeEncoder.encode(keyStr))){
    			return redisClient.hget(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key);
    		}
    		redisClient.hdel(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key);
    		return null;
    	}
    }
    
    public byte[] get(RedisClientKeyLibraryCode keyLibrary,byte[] key,String namespace,String appCode) throws Exception {
    	String keyStr=keyLibrary.getAppKeyCode(appCode)+SafeEncoder.encode(key);
    	if(redisClient.exists(namespace,SafeEncoder.encode(keyStr))){
    		return redisClient.hget(namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode(appCode)),key);
    	}else{
    		if(redisClient.hexists(namespace, SafeEncoder.encode("ALL_KEY_REG_" + appCode), SafeEncoder.encode(keyStr))){
    			return redisClient.hget(namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode(appCode)),key);
    		}
    		redisClient.hdel(namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode(appCode)),key);
    		return null;
    	}
    }

    public Map<byte[], byte[]> getAllByte(RedisClientKeyLibraryCode keyLibrary) throws Exception{
    	return redisClient.hgetAll(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()));
    }
    
    public Map<byte[], byte[]> getAllByte(RedisClientKeyLibraryCode keyLibrary,String namespace,String appCode) throws Exception{
    	return redisClient.hgetAll(namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode(appCode)));
    }
    public Map<String, String> getAll(RedisClientKeyLibraryCode keyLibrary) throws Exception{
    	return redisClient.hgetAll(this.namespace, keyLibrary.getAppKeyCode());
    }
    public Map<String, String> getAll(RedisClientKeyLibraryCode keyLibrary,String namespace,String appCode) throws Exception{
    	return redisClient.hgetAll(namespace, keyLibrary.getAppKeyCode(appCode));
    }

    public Long del(RedisClientKeyLibraryCode keyLibrary, String key) throws Exception {
    	redisClient.del(this.namespace,keyLibrary.getAppKeyCode()+key);
    	return redisClient.hdel(this.namespace,keyLibrary.getAppKeyCode(), key);
    }
    
    public Long del(RedisClientKeyLibraryCode keyLibrary, String key, String namespace,String appCode) throws Exception {
    	redisClient.del(namespace,keyLibrary.getAppKeyCode(appCode)+key);
    	return redisClient.hdel(namespace,keyLibrary.getAppKeyCode(appCode), key);
    }
   
    public Long del(RedisClientKeyLibraryCode keyLibrary, byte[] hashKey) throws Exception {
    	String key=keyLibrary.getAppKeyCode()+SafeEncoder.encode(hashKey);
    	redisClient.del(this.namespace,SafeEncoder.encode(key));
    	return redisClient.hdel(this.namespace,SafeEncoder.encode(keyLibrary.getAppKeyCode()), hashKey);
    }
    
    public Long del(RedisClientKeyLibraryCode keyLibrary, byte[] hashKey, String namespace,String appCode) throws Exception {
    	String key=keyLibrary.getAppKeyCode()+SafeEncoder.encode(hashKey);
    	redisClient.del(namespace,SafeEncoder.encode(key));
    	return redisClient.hdel(namespace,SafeEncoder.encode(keyLibrary.getAppKeyCode(appCode)), hashKey);
    }

    public Long del(RedisClientKeyLibraryCode keyLibrary) throws Exception {
    	Long count=redisClient.del(this.namespace,keyLibrary.getAppKeyCode());
    	return count+redisClient.del(this.namespace,SafeEncoder.encode(keyLibrary.getAppKeyCode()));
    }

    //exists
    public boolean exists(RedisClientKeyLibraryCode keyLibrary,String key) throws Exception{
    	if(redisClient.exists(this.namespace,keyLibrary.getAppKeyCode()+key)){
    		return true;
    	}else{
    		if(redisClient.hexists(this.namespace, allkeyreg, keyLibrary.getAppKeyCode()+key)){
    			
    			return redisClient.hget(this.namespace, keyLibrary.getAppKeyCode(),key)!=null?true:false;
    		}
    		redisClient.hdel(this.namespace, keyLibrary.getAppKeyCode(),key);
    		return false;
    	}
    	//return redisClient.hexists(this.namespace,keyLibrary.getAppKeyCode(), key);
    }

    public boolean exists(RedisClientKeyLibraryCode keyLibrary,byte[] key) throws Exception{
    	String keyStr=keyLibrary.getAppKeyCode()+SafeEncoder.encode(key);
    	if(redisClient.exists(this.namespace,SafeEncoder.encode(keyStr))){
    		return true;
    	}else{
    		if(redisClient.hexists(this.namespace, SafeEncoder.encode(allkeyreg), SafeEncoder.encode(keyStr))){
    			return redisClient.hget(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key)!=null?true:false;
    		}
    		redisClient.hdel(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()),key);
    		return false;
    	}
    	//return redisClient.hexists(this.namespace,SafeEncoder.encode(keyLibrary.getAppKeyCode()), key);
    }

    public Long expire(RedisClientKeyLibraryCode keyLibrary,String keyName, int seconds) throws Exception{
    	if(!redisClient.exists(this.namespace, keyLibrary.getAppKeyCode()+keyName)){
    		redisClient.set(this.namespace, keyLibrary.getAppKeyCode()+keyName, EMPTY_STRING_VALUE);
    	}
    	redisClient.hdel(this.namespace, allkeyreg,keyLibrary.getAppKeyCode()+keyName);
    	redisClient.expire(this.namespace, appCode+keyName, seconds);
    	return redisClient.expire(this.namespace, keyLibrary.getAppKeyCode()+keyName, seconds);
    }

    public Long expire(RedisClientKeyLibraryCode keyLibrary,byte[] keyName, int seconds) throws Exception{
    	String key=keyLibrary.getAppKeyCode()+SafeEncoder.encode(keyName);
    	if(!redisClient.exists(this.namespace, SafeEncoder.encode(key))){
    		redisClient.set(this.namespace, SafeEncoder.encode(key), EMPTY_BYTE_VALUE);
    	}
    	redisClient.hdel(this.namespace, SafeEncoder.encode(allkeyreg),SafeEncoder.encode(key));
    	redisClient.expire(this.namespace, SafeEncoder.encode(appCode+SafeEncoder.encode(keyName)), seconds);
    	return redisClient.expire(this.namespace, SafeEncoder.encode(key), seconds);
    }

    public Long expire(RedisClientKeyLibraryCode keyLibrary, int seconds) throws Exception{
    	redisClient.expire(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()), seconds);
    	return redisClient.expire(this.namespace, keyLibrary.getAppKeyCode(), seconds);
    }

    public Long setnx(RedisClientKeyLibraryCode keyLibrary, String key, String value) throws Exception{
    	redisClient.hsetnx(this.namespace, allkeyreg,keyLibrary.getAppKeyCode()+key,EMPTY_STRING_VALUE);
    	return redisClient.hsetnx(this.namespace, keyLibrary.getAppKeyCode(), key, value);
    }
    public Long setnx(RedisClientKeyLibraryCode keyLibrary, byte[] key, byte[] value) throws Exception{
    	String keyStr=keyLibrary.getAppKeyCode()+SafeEncoder.encode(key);
    	redisClient.hsetnx(this.namespace, SafeEncoder.encode(allkeyreg),SafeEncoder.encode(keyStr),EMPTY_BYTE_VALUE);
    	return redisClient.hsetnx(this.namespace, SafeEncoder.encode(keyLibrary.getAppKeyCode()), key, value);
    }


    //私用缓存数据
    public Long hset(RedisClientKeyLibraryCode keyLibrary,String hashkey, String key, String value) throws Exception {
    	redisClient.hset(this.namespace, personal_hashkey,appCode+hashkey,EMPTY_STRING_VALUE);
    	return redisClient.hset(this.namespace, appCode+hashkey,key,value);
    }

    public Long hset(RedisClientKeyLibraryCode keyLibrary,byte[] hashkey, byte[] key, byte[] value) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	redisClient.hset(this.namespace, SafeEncoder.encode(personal_hashkey),SafeEncoder.encode(hashKeyStr),EMPTY_BYTE_VALUE);
    	return redisClient.hset(this.namespace, SafeEncoder.encode(hashKeyStr),key,value);
    }
    
    public Long hset(String hashkey, String key, String value) throws Exception {
    	redisClient.hset(this.namespace, allhashkeyreg,appCode+hashkey,EMPTY_STRING_VALUE);
    	return redisClient.hset(this.namespace, appCode+hashkey,key,value);
    }

    public Long hset(byte[] hashkey, byte[] key, byte[] value) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	redisClient.hset(this.namespace, SafeEncoder.encode(allhashkeyreg),SafeEncoder.encode(hashKeyStr),EMPTY_BYTE_VALUE);
    	return redisClient.hset(this.namespace, SafeEncoder.encode(hashKeyStr),key,value);
    }

    public String hget(String hashkey, String key) throws Exception {
    	return redisClient.hget(this.namespace, appCode+hashkey,key);
    }
    
    public String hget(String hashkey, String key,String namespace,String appCode) throws Exception {
    	return redisClient.hget(namespace, appCode+hashkey,key);
    }

    public byte[] hget(byte[] hashkey,byte[] key) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.hget(this.namespace, SafeEncoder.encode(hashKeyStr),key);
    }

    public byte[] hget(byte[] hashkey,byte[] key,String namespace,String appCode) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.hget(namespace, SafeEncoder.encode(hashKeyStr),key);
    }
    
    public Map<byte[], byte[]> hgetAllByte(byte[] hashkey) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.hgetAll(this.namespace, SafeEncoder.encode(hashKeyStr));
    }
    
    public Map<byte[], byte[]> hgetAllByte(byte[] hashkey,String namespace,String appCode) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.hgetAll(namespace, SafeEncoder.encode(hashKeyStr));
    }

    public Map<String, String> hgetAll(String hashkey) throws Exception{
    	return redisClient.hgetAll(this.namespace, appCode+hashkey);
    }
    public Map<String, String> hgetAll(String hashkey,String namespace,String appCode) throws Exception{
    	return redisClient.hgetAll(namespace, appCode+hashkey);
    }

    //hexists
    public boolean hexists(String hashkey,String key) throws Exception{
    	return redisClient.hexists(this.namespace,appCode+hashkey, key);
    }

    public boolean hexists(byte[] hashkey,byte[] key) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.hexists(this.namespace,SafeEncoder.encode(hashKeyStr), key);
    }

    public Long hexpire(String hashkey, int seconds) throws Exception{
    	return redisClient.expire(this.namespace, appCode+hashkey, seconds);
    }

    public Long hexpire(byte[] hashkey, int seconds) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	return redisClient.expire(this.namespace, SafeEncoder.encode(hashKeyStr), seconds);
    }

    public Long hdel(String hashkey, String key) throws Exception {
    	return redisClient.hdel(this.namespace,appCode+hashkey,key);
    }

    public Long hdel(byte[] hashKey, byte[] key) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashKey);
    	return 	redisClient.hdel(this.namespace,SafeEncoder.encode(hashKeyStr),key);
    }
    
    public Long hdel(String hashkey, String key,String namespace,String appCode) throws Exception {
    	return redisClient.hdel(namespace,appCode+hashkey,key);
    }

    public Long hdel(byte[] hashKey, byte[] key,String namespace,String appCode) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashKey);
    	return 	redisClient.hdel(namespace,SafeEncoder.encode(hashKeyStr),key);
    }

    public Long del(String hashkey) throws Exception {
    	redisClient.hdel(this.namespace,allhashkeyreg,appCode+hashkey);
    	return redisClient.del(this.namespace,appCode+hashkey);
    }
    public Long del(byte[] hashkey) throws Exception {
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	redisClient.hdel(this.namespace,SafeEncoder.encode(allhashkeyreg),SafeEncoder.encode(hashKeyStr));
    	return redisClient.del(this.namespace,SafeEncoder.encode(hashKeyStr));
    }

    public Long hsetnx(String hashkey, String key, String value) throws Exception{
    	redisClient.hsetnx(this.namespace, allhashkeyreg,appCode+hashkey,EMPTY_STRING_VALUE);
    	return redisClient.hsetnx(this.namespace, appCode+hashkey,key, value);
    }
    public Long hsetnx(byte[] hashkey, byte[] key, byte[] value) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashkey);
    	redisClient.hsetnx(this.namespace, SafeEncoder.encode(allhashkeyreg),SafeEncoder.encode(hashKeyStr),EMPTY_BYTE_VALUE);
    	return redisClient.hsetnx(this.namespace, SafeEncoder.encode(hashKeyStr), key, value);
    }


    public void clearCahce() throws Exception {
    	for(RedisClientKeyLibraryCode cacheType:RedisClientKeyLibraryCode.values()){
			if(cacheType!=RedisClientKeyLibraryCode.SESSIONBYTE&&cacheType!=RedisClientKeyLibraryCode.SESSION&&cacheType!=RedisClientKeyLibraryCode.PERSONALBYTE&&cacheType!=RedisClientKeyLibraryCode.PERSONAL){
				del(cacheType);
			}
		}
    	
    	Set<String> allHashKey = keys(allhashkeyreg);
		for(String hashKey: allHashKey){
			redisClient.del(this.namespace,hashKey);
		}
		Set<byte[]> ALLHASHKEY = keys(SafeEncoder.encode(allhashkeyreg));
		for(byte[] hashKey: ALLHASHKEY){
			redisClient.del(this.namespace,SafeEncoder.encode(hashKey));
		}
    	redisClient.del(this.namespace,allhashkeyreg);
    	redisClient.del(this.namespace,SafeEncoder.encode(allhashkeyreg));
    }

    public void clearCahceAll() throws Exception {
    	for(RedisClientKeyLibraryCode cacheType:RedisClientKeyLibraryCode.values()){
    		if(cacheType!=RedisClientKeyLibraryCode.SESSIONBYTE&&cacheType!=RedisClientKeyLibraryCode.SESSION){
				del(cacheType);
			}
		}
    	redisClient.del(this.namespace,allkeyreg);
    	redisClient.del(this.namespace,SafeEncoder.encode(allkeyreg));
    	
    	Set<String> personalHashKey = keys(personal_hashkey);
		for(String hashKey: personalHashKey){
			redisClient.del(this.namespace,hashKey);
		}
		Set<byte[]> personalHASHKEY = keys(SafeEncoder.encode(personal_hashkey));
		for(byte[] hashKey: personalHASHKEY){
			redisClient.del(this.namespace,SafeEncoder.encode(hashKey));
		}
    	redisClient.del(this.namespace,personal_hashkey);
    	redisClient.del(this.namespace,SafeEncoder.encode(personal_hashkey));

    }


    public Set<byte[]> keys(byte[] hashKey) throws Exception{
    	String hashKeyStr=appCode+SafeEncoder.encode(hashKey);
    	if(redisClient.hexists(this.namespace, SafeEncoder.encode(allhashkeyreg), SafeEncoder.encode(hashKeyStr))){
    		return redisClient.hkeys(this.namespace, SafeEncoder.encode(hashKeyStr));
    	}else{
    		return redisClient.hkeys(this.namespace, hashKey);
    	}

    }

    public Set<String> keys(String hashKey) throws Exception{
    	if(redisClient.hexists(this.namespace, allhashkeyreg, appCode+hashKey)){
    		return redisClient.hkeys(this.namespace, appCode+hashKey);
    	}else{
    		return redisClient.hkeys(this.namespace, hashKey);
    	}
    }
    public long increment(RedisClientKeyLibraryCode keyLibrary,String keyName,long num)throws Exception{
        long number = 0L;
        number = redisClient.incrBy(getNamespace(), keyLibrary.getAppKeyCode()+keyName, num);
        set(keyLibrary,keyName,number+"");
        if(keyLibrary==RedisClientKeyLibraryCode.PERSONAL){
        	redisClient.hset(this.namespace, personal_hashkey,RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        }else{
        	redisClient.hset(this.namespace, allhashkeyreg,keyLibrary.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        }
        return number;
    }

    public long decrement(RedisClientKeyLibraryCode keyLibrary,String keyName,long num)throws Exception{
        long number = 0L;
        number = redisClient.decrBy(getNamespace(), keyLibrary.getAppKeyCode()+keyName, num);
        set(keyLibrary,keyName,number+"");
        if(keyLibrary==RedisClientKeyLibraryCode.PERSONAL){
        	redisClient.hset(this.namespace, personal_hashkey,RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        }else{
        	redisClient.hset(this.namespace, allhashkeyreg,keyLibrary.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        }
        return number;
    }
    public long increment(String keyName,long num)throws Exception{
        long number = 0L;
        number = redisClient.incrBy(getNamespace(), RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName, num);
        set(RedisClientKeyLibraryCode.PERSONAL,keyName,number+"");
        redisClient.hset(this.namespace, personal_hashkey,RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        return number;
    }

    public long decrement(String keyName,long num)throws Exception{
        long number = 0L;
        number = redisClient.decrBy(getNamespace(), RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName, num);
        set(RedisClientKeyLibraryCode.PERSONAL,keyName,number+"");
        redisClient.hset(this.namespace, personal_hashkey,RedisClientKeyLibraryCode.PERSONAL.getAppKeyCode()+keyName,EMPTY_STRING_VALUE);
        return number;
    }
    public Map<String, String> hgetAllRedis(String hashKey)throws Exception{
        Map<String, String> map = null;
        map = redisClient.hgetAll(getNamespace(), appCode+hashKey);
        return map;
    }
    public long hincrement(RedisClientKeyLibraryCode keyLibrary,String hashKey,String key,long num)throws Exception{
        long number = 0L;
    	redisClient.hset(this.namespace, personal_hashkey,appCode+hashKey,EMPTY_STRING_VALUE);
        number = redisClient.hincrBy(getNamespace(),appCode+hashKey, key, num);
        return number;
    }
    public long hincrement(String hashKey,String key,long num)throws Exception{
        long number = 0L;
    	redisClient.hset(this.namespace, allhashkeyreg,appCode+hashKey,EMPTY_STRING_VALUE);
        number = redisClient.hincrBy(getNamespace(),appCode+hashKey, key, num);
        return number;
    }
    
    public String set(String s1, String s2, String s3, String s4, long l)throws Exception{
    	return redisClient.set(this.namespace, s1,s2,s3,s4,l);
    }
    

    public RedisClient getRedisClient() {
        return redisClient;
    }
    public String getNamespace() {
        return namespace;
    }
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

	public RedisClient getRedisClient2() {
		return redisClient2;
	}

	public void setRedisClient2(RedisClient redisClient2) {
		this.redisClient2 = redisClient2;
	}
    
    
}