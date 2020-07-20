package com.l319.eduo2o.cache;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.util.SafeEncoder;

/**
 * 
 *
 * @author likunrui
 * @version 1.0
 */
public class JedisUtil {
	private Keys KEYS;
	// 对存储结构为String类型的操作
	private Strings STRINGS;
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
		this.jedisPool = jedisPoolWriper.getJedisPool();
	}

	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	public class Keys {
		/**
		 * 清空所有的key
		 * 
		 * @return
		 */
		public String flushAll() {
			Jedis jedis = getJedis();
			String stata = jedis.flushAll();
			jedis.close();
			return stata;
		}

		/**
		 * 删除keys对应的记录,可以是多个key
		 * 
		 * @param keys
		 * @return
		 */
		public long del(String... keys) {
			Jedis jedis = getJedis();
			long count = jedis.del(keys);
			jedis.close();
			return count;
		}

		/**
		 * 判断key是否存在
		 * 
		 * @param key
		 * @return
		 */
		public boolean exists(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			boolean exis = sjedis.exists(key);
			sjedis.close();
			return exis;
		}

		/**
		 * 查找所有匹配给定的模式的键
		 * 
		 * @param pattern
		 * @return
		 */
		public Set<String> keys(String pattern) {
			Jedis jedis = getJedis();
			Set<String> set = jedis.keys(pattern);
			jedis.close();
			return set;
		}

	}

	public class Strings {
		/**
		 * 根据key获取记录
		 * 
		 * @param key
		 * @return
		 */
		public String get(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			String value = sjedis.get(key);
			sjedis.close();
			return value;
		}

		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 * 
		 * @param key
		 * @param value
		 * @return
		 */
		public String set(String key, String value) {
			return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
		}

		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 * 
		 * @param key
		 * @param value
		 * @return
		 */
		public String set(String key, byte[] value) {
			return set(SafeEncoder.encode(key), value);
		}

		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 * 
		 * @param key
		 * @param value
		 * @return
		 */
		public String set(byte[] key, byte[] value) {
			Jedis jedis = getJedis();
			String status = jedis.set(key, value);
			jedis.close();
			return status;
		}
	}
}
