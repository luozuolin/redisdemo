package com.example.core.redis.redispool;

import com.example.core.redis.common.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisClient {

	private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

	private JedisPool jedisPool; // 池化管理jedis链接池

	public RedisClient(JedisPoolConfig jedisConfig, String ip, int port) {
		// 初始化连接池
		jedisPool = new JedisPool(jedisConfig, ip, port);
	}

	public String set(String s, String s1, String s2) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(s + s1, s2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String set(String s, byte[] bytes, byte[] bytes1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(addBytes(s.getBytes(), bytes), bytes1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Object eval(String script, List<String> keys, List<String> args){
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.eval(script, keys, args);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null; 
	}
	
	
	public String set(String s, String s1, String s2, String s3, String s4, long l)
			 {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(s1, s2, s3, s4, l);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String set(String s, byte[] bytes, byte[] bytes1, byte[] bytes2, byte[] bytes3, long l)
			 {
		return null;
	}

	public String get(String s, String s1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(s + s1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public byte[] get(String s, byte[] bytes) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(addBytes(s.getBytes(), bytes));
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Boolean exists(String s, String s1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(s + s1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Boolean exists(String s, byte[] bytes) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(addBytes(s.getBytes(), bytes));
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long persist(String s, String s1) {
		return null;
	}

	public Long persist(String s, byte[] bytes) {
		return null;
	}

	public String type(String s, String s1) {
		return null;
	}

	public String type(String s, byte[] bytes) {
		return null;
	}

	public Long expire(String s, String s1, int i) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(s + s1, i);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long expire(String s, byte[] bytes, int i) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(addBytes(s.getBytes(), bytes), i);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long pexpire(String s, String s1, long l) {
		return null;
	}

	public Long pexpire(String s, byte[] bytes, long l) {
		return null;
	}

	public Long expireAt(String s, String s1, long l) {
		return null;
	}

	public Long expireAt(String s, byte[] bytes, long l) {
		return null;
	}

	public Long pexpireAt(String s, String s1, long l) {
		return null;
	}

	public Long pexpireAt(String s, byte[] bytes, long l) {
		return null;
	}

	public Long ttl(String s, String s1) {
		return null;
	}

	public Long ttl(String s, byte[] bytes) {
		return null;
	}

	public Long pttl(String s, String s1) {
		return null;
	}

	public Boolean setbit(String s, String s1, long l, boolean b) {
		return null;
	}

	public Boolean setbit(String s, byte[] bytes, long l, boolean b)
			 {
		return null;
	}

	public Boolean setbit(String s, String s1, long l, String s2) {
		return null;
	}

	public Boolean setbit(String s, byte[] bytes, long l, byte[] bytes1)
			 {
		return null;
	}

	public Boolean getbit(String s, String s1, long l) {
		return null;
	}

	public Boolean getbit(String s, byte[] bytes, long l) {
		return null;
	}

	public Long setrange(String s, String s1, long l, String s2) {
		return null;
	}

	public Long setrange(String s, byte[] bytes, long l, byte[] bytes1)
			 {
		return null;
	}

	public String getrange(String s, String s1, long l, long l1) {
		return null;
	}

	public byte[] getrange(String s, byte[] bytes, long l, long l1) {
		return null;
	}

	public String getSet(String s, String s1, String s2) {
		return null;
	}

	public byte[] getSet(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public Long setnx(String s, String s1, String s2) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.setnx(s + s1, s2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long setnx(String s, byte[] bytes, byte[] bytes1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.setnx(addBytes(s.getBytes(), bytes), bytes1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String setex(String s, String s1, int i, String s2) {
		return null;
	}

	public String setex(String s, byte[] bytes, int i, byte[] bytes1)
			 {
		return null;
	}

	public String psetex(String s, String s1, long l, String s2) {
		return null;
	}

	public Long decrBy(String s, String s1, long l) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.decrBy(s + s1, l);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long decrBy(String s, byte[] bytes, long l) {
		return null;
	}

	public Long decr(String s, String s1) {
		return null;
	}

	public Long decr(String s, byte[] bytes) {
		return null;
	}

	public Long incrBy(String s, String s1, long l) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(s + s1, l);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long incrBy(String s, byte[] bytes, long l) {
		return null;
	}

	public Double incrByFloat(String s, String s1, double v) {
		return null;
	}

	public Double incrByFloat(String s, byte[] bytes, double v) {
		return null;
	}

	public Long incr(String s, String s1) {
		return null;
	}

	public Long incr(String s, byte[] bytes) {
		return null;
	}

	public Long append(String s, String s1, String s2) {
		return null;
	}

	public Long append(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public String substr(String s, String s1, int i, int i1) {
		return null;
	}

	public byte[] substr(String s, byte[] bytes, int i, int i1) {
		return null;
	}

	public Long hset(String s, String s1, String s2, String s3) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(s + s1, s2, s3);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static byte[] addBytes(byte[] data1, byte[] data2) {
		byte[] data3 = new byte[data1.length + data2.length];
		System.arraycopy(data1, 0, data3, 0, data1.length);
		System.arraycopy(data2, 0, data3, data1.length, data2.length);
		return data3;

	}

	public Long hset(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(addBytes(s.getBytes(), bytes), bytes1, bytes2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String hget(String s, String s1, String s2) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(s + s1, s2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public byte[] hget(String s, byte[] bytes, byte[] bytes1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(addBytes(s.getBytes(), bytes), bytes1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hsetnx(String s, String s1, String s2, String s3) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hsetnx(s + s1, s2, s3);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hsetnx(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hsetnx(addBytes(s.getBytes(), bytes), bytes1, bytes2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String hmset(String s, String s1, Map<String, String> map)
			 {
		return null;
	}

	public String hmset(String s, byte[] bytes, Map<byte[], byte[]> map)
			 {
		return null;
	}

	public List<String> hmget(String s, String s1, String... strings)
			 {
		return null;
	}

	public List<byte[]> hmget(String s, byte[] bytes, byte[]... bytes1)
			 {
		return null;
	}

	public Long hincrBy(String s, String s1, String s2, long l) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hincrBy(s + s1, s2, l);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hincrBy(String s, byte[] bytes, byte[] bytes1, long l)
			 {
		return null;
	}

	public Double hincrByFloat(String s, String s1, String s2, double v)
			 {
		return null;
	}

	public Double hincrByFloat(String s, byte[] bytes, byte[] bytes1, double v)
			 {
		return null;
	}

	public Boolean hexists(String s, String s1, String s2) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hexists(s + s1, s2);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;

	}

	public Boolean hexists(String s, byte[] bytes, byte[] bytes1) {

		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hexists(addBytes(s.getBytes(), bytes), bytes1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hdel(String s, String s1, String... strings) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(s + s1, strings);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hdel(String s, byte[] bytes, byte[]... bytes1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(addBytes(s.getBytes(), bytes), bytes1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long hlen(String s, String s1) {
		return null;
	}

	public Long hlen(String s, byte[] bytes) {
		return null;
	}

	public Set<String> hkeys(String s, String s1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			logger.warn("即将调用有损性能的方法...");
			jedis = jedisPool.getResource();
			return jedis.hkeys(s + s1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Set<byte[]> hkeys(String s, byte[] bytes) {
		Jedis jedis = null;
		boolean success = true;
		try {
			logger.warn("即将调用有损性能的方法...");
			jedis = jedisPool.getResource();
			return jedis.hkeys(addBytes(s.getBytes(), bytes));
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public List<String> hvals(String s, String s1) {
		return null;
	}

	public Collection<byte[]> hvals(String s, byte[] bytes) {
		return null;
	}

	public Map<String, String> hgetAll(String s, String s1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			logger.warn("即将调用有损性能的方法...");
			jedis = jedisPool.getResource();
			return jedis.hgetAll(s + s1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Map<byte[], byte[]> hgetAll(String s, byte[] bytes) {
		Jedis jedis = null;
		boolean success = true;
		try {
			logger.warn("即将调用有损性能的方法...");
			jedis = jedisPool.getResource();
			return jedis.hgetAll(addBytes(s.getBytes(), bytes));
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}

		return null;
	}

	public Long rpush(String s, String s1, String... strings) {
		return null;
	}

	public Long rpush(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Long lpush(String s, String s1, String... strings) {
		return null;
	}

	public Long lpush(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Long llen(String s, String s1) {
		return null;
	}

	public Long llen(String s, byte[] bytes) {
		return null;
	}

	public List<String> lrange(String s, String s1, long l, long l1)
			 {
		return null;
	}

	public List<byte[]> lrange(String s, byte[] bytes, long l, long l1)
			 {
		return null;
	}

	public String ltrim(String s, String s1, long l, long l1) {
		return null;
	}

	public String ltrim(String s, byte[] bytes, long l, long l1) {
		return null;
	}

	public String lindex(String s, String s1, long l) {
		return null;
	}

	public byte[] lindex(String s, byte[] bytes, long l) {
		return null;
	}

	public String lset(String s, String s1, long l, String s2) {
		return null;
	}

	public String lset(String s, byte[] bytes, long l, byte[] bytes1)
			 {
		return null;
	}

	public Long lrem(String s, String s1, long l, String s2) {
		return null;
	}

	public Long lrem(String s, byte[] bytes, long l, byte[] bytes1) {
		return null;
	}

	public String lpop(String s, String s1) {
		return null;
	}

	public byte[] lpop(String s, byte[] bytes) {
		return null;
	}

	public String rpop(String s, String s1) {
		return null;
	}

	public byte[] rpop(String s, byte[] bytes) {
		return null;
	}

	public Long sadd(String s, String s1, String... strings) {
		return null;
	}

	public Long sadd(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Set<String> smembers(String s, String s1) {
		return null;
	}

	public Set<byte[]> smembers(String s, byte[] bytes) {
		return null;
	}

	public Long srem(String s, String s1, String... strings) {
		return null;
	}

	public Long srem(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public String spop(String s, String s1) {
		return null;
	}

	public byte[] spop(String s, byte[] bytes) {
		return null;
	}

	public Set<String> spop(String s, String s1, long l) {
		return null;
	}

	public Set<byte[]> spop(String s, byte[] bytes, long l) {
		return null;
	}

	public Long scard(String s, String s1) {
		return null;
	}

	public Long scard(String s, byte[] bytes) {
		return null;
	}

	public Boolean sismember(String s, String s1, String s2) {
		return null;
	}

	public Boolean sismember(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public String srandmember(String s, String s1) {
		return null;
	}

	public byte[] srandmember(String s, byte[] bytes) {
		return null;
	}

	public List<String> srandmember(String s, String s1, int i)
			 {
		return null;
	}

	public List<byte[]> srandmember(String s, byte[] bytes, int i)
			 {
		return null;
	}

	public Long strlen(String s, String s1) {
		return null;
	}

	public Long strlen(String s, byte[] bytes) {
		return null;
	}

	public Long zadd(String s, String s1, double v, String s2) {
		return null;
	}

	public Long zadd(String s, byte[] bytes, double v, byte[] bytes1)
			 {
		return null;
	}

	public Long zadd(String s, String s1, Map<String, Double> map)
			 {
		return null;
	}

	public Long zadd(String s, byte[] bytes, Map<byte[], Double> map)
			 {
		return null;
	}

	public Set<String> zrange(String s, String s1, long l, long l1) {
		return null;
	}

	public Set<byte[]> zrange(String s, byte[] bytes, long l, long l1)
			 {
		return null;
	}

	public Long zrem(String s, String s1, String... strings) {
		return null;
	}

	public Long zrem(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Double zincrby(String s, String s1, double v, String s2) {
		return null;
	}

	public Double zincrby(String s, byte[] bytes, double v, byte[] bytes1)
			 {
		return null;
	}

	public Long zrank(String s, String s1, String s2) {
		return null;
	}

	public Long zrank(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public Long zrevrank(String s, String s1, String s2) {
		return null;
	}

	public Long zrevrank(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public Set<String> zrevrange(String s, String s1, long l, long l1)
			 {
		return null;
	}

	public Set<byte[]> zrevrange(String s, byte[] bytes, long l, long l1)
			 {
		return null;
	}

	public Long zcard(String s, String s1) {
		return null;
	}

	public Long zcard(String s, byte[] bytes) {
		return null;
	}

	public Double zscore(String s, String s1, String s2) {
		return null;
	}

	public Double zscore(String s, byte[] bytes, byte[] bytes1) {
		return null;
	}

	public List<String> sort(String s, String s1) {
		return null;
	}

	public List<byte[]> sort(String s, byte[] bytes) {
		return null;
	}

	public Long zcount(String s, String s1, double v, double v1) {
		return null;
	}

	public Long zcount(String s, byte[] bytes, double v, double v1) {
		return null;
	}

	public Long zcount(String s, String s1, String s2, String s3) {
		return null;
	}

	public Long zcount(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrangeByScore(String s, String s1, double v, double v1)
			 {
		return null;
	}

	public Set<byte[]> zrangeByScore(String s, byte[] bytes, double v, double v1)
			 {
		return null;
	}

	public Set<String> zrangeByScore(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Set<byte[]> zrangeByScore(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrevrangeByScore(String s, String s1, double v, double v1)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByScore(String s, byte[] bytes, double v, double v1)
			 {
		return null;
	}

	public Set<String> zrangeByScore(String s, String s1, double v, double v1, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrangeByScore(String s, byte[] bytes, double v, double v1, int i, int i1)
			 {
		return null;
	}

	public Set<String> zrevrangeByScore(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByScore(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrangeByScore(String s, String s1, String s2, String s3, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrangeByScore(String s, byte[] bytes, byte[] bytes1, byte[] bytes2, int i, int i1)
			 {
		return null;
	}

	public Set<String> zrevrangeByScore(String s, String s1, double v, double v1, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByScore(String s, byte[] bytes, double v, double v1, int i, int i1)
			 {
		return null;
	}

	public Set<String> zrevrangeByScore(String s, String s1, String s2, String s3, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByScore(String s, byte[] bytes, byte[] bytes1, byte[] bytes2, int i, int i1)
			 {
		return null;
	}

	public Long zremrangeByRank(String s, String s1, long l, long l1)
			 {
		return null;
	}

	public Long zremrangeByRank(String s, byte[] bytes, long l, long l1)
			 {
		return null;
	}

	public Long zremrangeByScore(String s, String s1, double v, double v1)
			 {
		return null;
	}

	public Long zremrangeByScore(String s, byte[] bytes, double v, double v1)
			 {
		return null;
	}

	public Long zremrangeByScore(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Long zremrangeByScore(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Long zlexcount(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Long zlexcount(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrangeByLex(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Set<byte[]> zrangeByLex(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrangeByLex(String s, String s1, String s2, String s3, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrangeByLex(String s, byte[] bytes, byte[] bytes1, byte[] bytes2, int i, int i1)
			 {
		return null;
	}

	public Set<String> zrevrangeByLex(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByLex(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Set<String> zrevrangeByLex(String s, String s1, String s2, String s3, int i, int i1)
			 {
		return null;
	}

	public Set<byte[]> zrevrangeByLex(String s, byte[] bytes, byte[] bytes1, byte[] bytes2, int i, int i1)
			 {
		return null;
	}

	public Long zremrangeByLex(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Long zremrangeByLex(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public Long lpushx(String s, String s1, String... strings) {
		return null;
	}

	public Long lpushx(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Long rpushx(String s, String s1, String... strings) {
		return null;
	}

	public Long rpushx(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Long del(String s, String s1) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(s + s1);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public Long del(String s, byte[] bytes) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(addBytes(s.getBytes(), bytes));
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public String echo(String s) {
		return null;
	}

	public Long bitcount(String s, String s1) {
		return null;
	}

	public Long bitcount(String s, byte[] bytes) {
		return null;
	}

	public Long bitcount(String s, String s1, long l, long l1) {
		return null;
	}

	public Long bitcount(String s, byte[] bytes, long l, long l1) {
		return null;
	}

	public Long bitpos(String s, String s1, boolean b) {
		return null;
	}

	public Long pfadd(String s, String s1, String... strings) {
		return null;
	}

	public Long pfadd(String s, byte[] bytes, byte[]... bytes1) {
		return null;
	}

	public Long pfcount(String s, String s1) {
		return null;
	}

	public Long pfcount(String s, byte[] bytes) {
		return null;
	}

	public Long geoadd(String s, String s1, double v, double v1, String s2)
			 {
		return null;
	}

	public Long geoadd(String s, byte[] bytes, double v, double v1, byte[] bytes1)
			 {
		return null;
	}

	public Double geodist(String s, String s1, String s2, String s3)
			 {
		return null;
	}

	public Double geodist(String s, byte[] bytes, byte[] bytes1, byte[] bytes2)
			 {
		return null;
	}

	public List<String> geohash(String s, String s1, String... strings)
			 {
		return null;
	}

	public List<byte[]> geohash(String s, byte[] bytes, byte[]... bytes1)
			 {
		return null;
	}

	public boolean set(String key, String value) throws Exception {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 向缓存中设置对象
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
			return true;
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 删除缓存中得对象，根据key
	 *
	 * @param key
	 * @return
	 */
	public boolean del(String key) {
		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 根据key 获取内容
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key) {

		Jedis jedis = null;
		boolean success = true;
		try {
			jedis = jedisPool.getResource();
			byte[] value = jedis.get(key.getBytes());
			return SerializeUtil.unserialize(value);
		} catch (Exception e) {
			success = false;} finally {
			if (success && jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public static void hset() {

	}

	public static void main(String[] args) throws Exception {
		// //测试存储字符串
		// RedisClient.set("hello", "world");
		// System.out.println(RedisClient.get("hello"));
		// //测试存储对象
		// People people = new People();// 存放对象时 必须实现序列化
		// people.setName("wamhf");
		// people.setAge(26);
		// RedisClient.set("name1", people);
		// People p = (People) RedisClient.get("name1");
		// System.out.println(p.getAge() + "-------" + p.getName());
		// //测试存储List
		// List list = new ArrayList();
		// list.add("111");
		// list.add("222");
		// list.add("333");
		// RedisClient.set("list", list);
		// List list1 = (List) RedisClient.get("list");
		// for (Object o : list1) {
		// System.out.println(o.toString());
		// }
		// //测试存储Set
		// Set set = new HashSet<>();
		// set.add("a");
		// set.add(1);
		// RedisClient.set("set", set);
		// Set s = (Set) RedisClient.get("set");
		// for (Object o : s) {
		// System.out.println(o.toString());
		// }
		// System.out.println("---------------------验证set去重---------------------");
		// Set set1 = new HashSet<>();
		// set1.add("a");
		// set1.add(1);
		// RedisClient.set("set", set1);
		// Set s1 = (Set) RedisClient.get("set");
		// for (Object o : s1) {
		// System.out.println(o.toString());
		// }

		// RedisClient redisClient = new RedisClient();
		// redisClient.test();
	}

	@SuppressWarnings("rawtypes")
	public void test1() {
		Jedis redis = jedisPool.getResource();
		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// KEY操作
		// KEYS
		Set keys = redis.keys("*");// 列出所有的key，查找特定的key如：redis.keys("foo")
		Iterator t1 = keys.iterator();
		while (t1.hasNext()) {
			Object obj1 = t1.next();
			System.out.println(obj1 + ":" + redis.get(obj1.toString()));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test() {
		Jedis redis = jedisPool.getResource();
		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// KEY操作

		// KEYS
		Set keys = redis.keys("*");// 列出所有的key，查找特定的key如：redis.keys("foo")
		Iterator t1 = keys.iterator();
		while (t1.hasNext()) {
			Object obj1 = t1.next();
			System.out.println(obj1 + ":" + redis.get(obj1.toString()));
		}

		// DEL 移除给定的一个或多个key。如果key不存在，则忽略该命令。
		// redis.del("set");

		// TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)
		System.out.println(redis.ttl("hello"));

		// PERSIST key 移除给定key的生存时间。
		redis.persist("foo");

		// EXISTS 检查给定key是否存在。
		redis.exists("foo");

		// MOVE key db
		// 将当前数据库(默认为0)的key移动到给定的数据库db当中。如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定key，或者key不存在于当前数据库，那么MOVE没有任何效果。
		redis.move("foo", 1);// 将foo这个key，移动到数据库1

		// RENAME key newkey
		// 将key改名为newkey。当key和newkey相同或者key不存在时，返回一个错误。当newkey已经存在时，RENAME命令将覆盖旧值。
		redis.rename("foo", "foonew");

		// TYPE key 返回key所储存的值的类型。
		System.out.println(redis.type("foo"));// none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表)

		// EXPIRE key seconds 为给定key设置生存时间。当key过期时，它会被自动删除。
		redis.expire("foo", 5);// 5秒过期
		// EXPIREAT
		// EXPIREAT的作用和EXPIRE一样，都用于为key设置生存时间。不同在于EXPIREAT命令接受的时间参数是UNIX时间戳(unix
		// timestamp)。

		// 一般SORT用法 最简单的SORT使用方法是SORT key。
		redis.lpush("sort", "1");
		redis.lpush("sort", "4");
		redis.lpush("sort", "6");
		redis.lpush("sort", "3");
		redis.lpush("sort", "0");

		List list = redis.sort("sort");// 默认是升序
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// STRING 操作

		// SET key value将字符串值value关联到key。
		redis.set("name", "wangjun1");
		redis.set("id", "123456");
		redis.set("address", "guangzhou");

		// SETEX key seconds value将值value关联到key，并将key的生存时间设为seconds(以秒为单位)。
		redis.setex("foo", 5, "haha");

		// MSET key value [key value ...]同时设置一个或多个key-value对。
		redis.mset("haha", "111", "xixi", "222");

		// redis.flushAll();清空所有的key
		System.out.println(redis.dbSize());// dbSize是多少个key的个数

		// APPEND key value如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。
		redis.append("foo", "00");// 如果key已经存在并且是一个字符串，APPEND命令将value追加到key原来的值之后。

		// GET key 返回key所关联的字符串值
		redis.get("foo");

		// MGET key [key ...] 返回所有(一个或多个)给定key的值
		List list1 = redis.mget("haha", "xixi");
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}

		// DECR key将key中储存的数字值减一。
		// DECRBY key decrement将key所储存的值减去减量decrement。
		// INCR key 将key中储存的数字值增一。
		// INCRBY key increment 将key所储存的值加上增量increment。

		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// Hash 操作

		// HSET key field value将哈希表key中的域field的值设为value。
		redis.hset("website", "google", "www.google.cn");
		redis.hset("website", "baidu", "www.baidu.com");
		redis.hset("website", "sina", "www.sina.com");

		// HMSET key field value [field value ...] 同时将多个field -
		// value(域-值)对设置到哈希表key中。
		Map map = new HashMap();
		map.put("cardid", "123456");
		map.put("username", "jzkangta");
		redis.hmset("hash", map);

		// HGET key field返回哈希表key中给定域field的值。
		System.out.println(redis.hget("hash", "username"));

		// HMGET key field [field ...]返回哈希表key中，一个或多个给定域的值。
		List list2 = redis.hmget("website", "google", "baidu", "sina");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}

		// HGETALL key返回哈希表key中，所有的域和值。
		Map<String, String> map1 = redis.hgetAll("hash");
		for (Map.Entry entry : map1.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
		}

		// HDEL key field [field ...]删除哈希表key中的一个或多个指定域。
		// HLEN key 返回哈希表key中域的数量。
		// HEXISTS key field查看哈希表key中，给定域field是否存在。
		// HINCRBY key field increment为哈希表key中的域field的值加上增量increment。
		// HKEYS key返回哈希表key中的所有域。
		// HVALS key返回哈希表key中的所有值。

		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// LIST 操作
		// LPUSH key value [value ...]将值value插入到列表key的表头。
		redis.lpush("list", "abc");
		redis.lpush("list", "xzc");
		redis.lpush("list", "erf");
		redis.lpush("list", "bnh");

		// LRANGE key start
		// stop返回列表key中指定区间内的元素，区间以偏移量start和stop指定。下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。
		List list3 = redis.lrange("list", 0, -1);
		for (int i = 0; i < list3.size(); i++) {
			System.out.println(list3.get(i));
		}

		// LLEN key返回列表key的长度。
		// LREM key count value根据参数count的值，移除列表中与参数value相等的元素。

		/*
		 * ---------------------------------------------------------------------
		 * --------------------------------------
		 */
		// SET 操作
		// SADD key member [member ...]将member元素加入到集合key当中。
		redis.sadd("testSet", "s1");
		redis.sadd("testSet", "s2");
		redis.sadd("testSet", "s3");
		redis.sadd("testSet", "s4");
		redis.sadd("testSet", "s5");

		// SREM key member移除集合中的member元素。
		redis.srem("testSet", "s5");

		// SMEMBERS key返回集合key中的所有成员。
		Set set = redis.smembers("testSet");
		Iterator t2 = set.iterator();
		while (t2.hasNext()) {
			Object obj1 = t2.next();
			System.out.println(obj1);
		}

		// SISMEMBER key member判断member元素是否是集合key的成员。是（true），否则（false）
		System.out.println(redis.sismember("testSet", "s4"));

		// SCARD key返回集合key的基数(集合中元素的数量)。
		// SMOVE source destination member将member元素从source集合移动到destination集合。

		// SINTER key [key ...]返回一个集合的全部成员，该集合是所有给定集合的交集。
		// SINTERSTORE destination key [key
		// ...]此命令等同于SINTER，但它将结果保存到destination集合，而不是简单地返回结果集
		// SUNION key [key ...]返回一个集合的全部成员，该集合是所有给定集合的并集。
		// SUNIONSTORE destination key [key
		// ...]此命令等同于SUNION，但它将结果保存到destination集合，而不是简单地返回结果集。
		// SDIFF key [key ...]返回一个集合的全部成员，该集合是所有给定集合的差集 。
		// SDIFFSTORE destination key [key
		// ...]此命令等同于SDIFF，但它将结果保存到destination集合，而不是简单地返回结果集。

	}

}
