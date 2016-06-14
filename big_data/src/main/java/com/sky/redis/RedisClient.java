package com.sky.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** Redis操作类
 * @ClassName: RedisClient
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午2:22:57
 * @version V1.0
 */
public class RedisClient {
	
	private JedisPool jedisPool;
	private static Jedis jedis;
	private static final String REDIS_HOST = "192.168.200.1";
	private static final int REDIS_PORT = 6379;
	
	/** 无参构造函数
	 * @Title: RedisClient
	 * @Description: 
	 */
	public RedisClient(){
		initialPool();
		jedis = jedisPool.getResource();
	}
	
	/** 初始化Redis连接池
	 * @MethodName: initialPool
	 * @Description: 
	 */
	private void initialPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		
		jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
	}
	
	private void returnResource(Jedis j){
		jedisPool.returnResource(j);
	}
	
	/** 测试方法
	 * @MethodName: main
	 * @Description: 
	 * @param args
	 */
	public static void main(String[] args) {
		RedisClient client = new RedisClient();
		client.initialPool();
		
		client.string();
		client.hash();
		client.linkedList();
		client.set();
		client.orderSet();
		
		client.returnResource(jedis);
	}
	
	/** 字符串
	 * @MethodName: string
	 * @Description: 
	 */
	private void string(){
		// 清空数据
		jedis.flushDB();
		
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		System.out.println("value001：----------->"+jedis.get("key001"));
		System.out.println("value002：----------->"+jedis.get("key002"));
	}
	
	/** 哈希
	 * @MethodName: hash
	 * @Description: 
	 */
	private void hash(){
		
	}
	
	/** 链表
	 * @MethodName: linkedList
	 * @Description: 
	 */
	private void linkedList(){
		
	}
	
	/** 集合
	 * @MethodName: set
	 * @Description: 
	 */
	private void set(){
		
	}
	
	/** 有序集合
	 * @MethodName: orderSet
	 * @Description: 
	 */
	private void orderSet(){
		
	}
}