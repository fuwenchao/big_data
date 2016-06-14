package com.sky.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/** Mongodb操作类
 * @ClassName: MongodbClient
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午4:48:37
 * @version V1.0
 */
public class MongodbClient {
	
	private Mongo mongo;
	private DB db;
	private static final String MONGO_HOST = "127.0.0.1";
	private static final int MONGO_PORT = 27017;
	private static final String DB_NAME = "sky_pm";
	
	@SuppressWarnings("deprecation")
	public MongodbClient(){
		try {
			mongo = new Mongo(MONGO_HOST, MONGO_PORT);
			db = mongo.getDB(DB_NAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/** 测试方法
	 * @MethodName: main
	 * @Description: 
	 * @param args
	 */
	public static void main(String[] args) {
		MongodbClient client = new MongodbClient();
		client.insert("sys_user");
		client.update();
		client.delete();
		client.drop();
	}
	
	/** 插入数据
	 * @MethodName: insert
	 * @Description: 
	 * @param collectionName
	 */
	private void insert(String collectionName){
		if(null != db){
			DBCollection dbCollect = db.getCollection(collectionName);
			
			DBObject dbObject = new BasicDBObject();
			dbObject.put("name", "liming");
			dbCollect.save(dbObject);
		}
	}
	
	private void update(){
		
	}
	
	private void delete(){
		
	}
	
	private void drop(){
		
	}
}