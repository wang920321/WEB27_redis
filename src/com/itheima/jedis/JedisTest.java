package com.itheima.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
    //通过Java程序访问redis数据库
	@Test
	//获得单一的jedis对象操作数据库
	public void test1(){
		//1获得连接对象
		Jedis jedis=new Jedis("192.168.217.128",6379);
		jedis.set("addr", "重庆");
		
		System.out.println(jedis.get("addr"));
	}
	
	//通过jedis的pool获得jedis连接池对象
	@Test
	public void test2(){
		//创建池子的配置对象
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxIdle(30);//最大闲置个数
		poolConfig.setMinIdle(10);//最小闲置个数
		poolConfig.setMaxTotal(50);//最大连接数
		//1创建一个redis的连接池
		JedisPool pool=new JedisPool(poolConfig, "192.168.217.128", 6379);
		//2从池子中获取redis的连接资源
		Jedis jedis = pool.getResource();
		//3操作数据库
		jedis.set("xxx", "yyy");
		System.out.println(jedis.get("xxx"));
		//4关闭资源
		jedis.close();
		pool.close();
		
	}
	public static void main(String[] args) {
		Jedis jedis = JedisPoolUtil.getJedis();
		System.out.println(jedis.get("xxx"));
		
	}
}
