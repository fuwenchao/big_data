package com.sky.mongodb;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/** Mongodb操作类
 * @ClassName: MongodbClient
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午4:48:37
 * @version V1.0
 */
public class MongodbClient {
	
	private MongoClient mongoClient;
	private static final String MONGO_HOST = "127.0.0.1";// 数据库IP地址
	private static final int MONGO_PORT = 27017;// 端口号
	private static final String DB_NAME = "sky_pm";// 数据库名
	private Logger logger = Logger.getLogger(MongodbClient.class);
	
	/** 无参构造函数
	 * @MethodName: MongodbClient
	 * @Description: 
	 */
	public MongodbClient(){
		try {
			mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
		} catch (UnknownHostException e) {
			logger.error(e);
		}
	}
	
	/** 测试方法
	 * @MethodName: main
	 * @Description: 
	 * @param args
	 */
	public static void main(String[] args) {
		MongodbClient client = new MongodbClient();
		client.getDbNameList();
		DB db = client.getDB();
		if(null != db){
			client.getCollectionList(db);
			// 添加数据
			client.insert(db, "sys_user");
			// 更新数据
			client.update(db, "sys_user");
			// 查询
			client.findAll(db, "sys_user");
			// 删除数据
//			client.delete(db, "sys_user");
			// 删除集合
//			client.dropCollection(db, "sys_user");
			// 删除数据库
//			db.dropDatabase();
			// 关闭DB
			db.requestDone();
			db = null;
		}
	}
	
	/** 获得所有数据库
	 * @MethodName: getDbNameList
	 * @Description: 
	 */
	private void getDbNameList(){
		StringBuffer sb = new StringBuffer("遍历所有数据库名称，分别为：");
		
		List<String> dbNameList = mongoClient.getDatabaseNames();
		for(String dbName : dbNameList){
			sb.append(dbName).append(" ");
		}
System.out.println(sb.toString());
	}
	
	/** 获得数据库
	 * @MethodName: getDB
	 * @Description: 
	 * @return DB
	 */
	private DB getDB(){
		DB db = null;
		if(null != mongoClient){
			db = mongoClient.getDB(DB_NAME);
		}
		return db;
	}
	
	/** 获得某个数据库中的所有集合
	 * @MethodName: getCollectionList
	 * @Description: 
	 * @param db
	 */
	private void getCollectionList(DB db){
		StringBuffer sb = new StringBuffer("获得【"+db.getName()+"】数据库下的所有集合名，分别为：");
		Set<String> collectNameSet = db.getCollectionNames();
		for(String collectionName : collectNameSet){
			sb.append(collectionName).append(" ");
		}
System.out.println(sb.toString());
	}
	
	/** 获得某个数据库下的集合对象
	 * @MethodName: getDBCollection
	 * @Description: 
	 * @param db
	 * @param collectionName
	 * @return DBCollection
	 */
	private DBCollection getDBCollection(DB db, String collectionName){
		DBCollection dbCollect = db.getCollection(collectionName);
		return dbCollect;
	}
	
	/** 判断数据是否存在
	 * @MethodName: isExist
	 * @Description: 
	 * @param db
	 * @param collectionName
	 * @param key
	 * @param value
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private boolean isExist(DB db, String collectionName, String key, String value){
		boolean isSuccess = false;
		
		DBCollection dbCollection = getDBCollection(db, collectionName);
		if(null != dbCollection){
			isSuccess = isExist(dbCollection, key, value);
		}
		return isSuccess;
	}
	
	/** 判断数据是否存在
	 * @MethodName: isExist
	 * @Description: 
	 * @param dbCollection
	 * @param key
	 * @param value
	 * @return boolean
	 */
	private boolean isExist(DBCollection dbCollection, String key, String value){
		DBObject query = new BasicDBObject();
		query.put(key, value);
		boolean isSuccess = dbCollection.count(query) > 0 ? true : false;
		return isSuccess;
	}
	
	/** 插入数据
	 * @MethodName: insert
	 * @Description: 
	 * @param db
	 * @param collectionName
	 */
	private void insert(DB db, String collectionName){
		DBCollection dbCollect = getDBCollection(db, collectionName);
		if(null != dbCollect){
			String userName_key = "user_name";
			String userName_value = "liming";
			if(!isExist(dbCollect, userName_key, userName_value)){// 不存在，则保存。
				DBObject dbObject = new BasicDBObject();
				dbObject.put("user_id", "1");
				dbObject.put(userName_key, userName_value);
				dbObject.put("user_pwd", "123456");
				dbObject.put("sex", "1");
				dbObject.put("email", "lili@eversec.com.cn");
				dbObject.put("post_code", "100085");
				dbObject.put("address", "北京市海淀区上地三街");
				WriteResult result = dbCollect.save(dbObject);
System.out.println("插入数据：------>"+(result.getN() == 0 ? "成功" : "失败")+"！");
			}else {
System.out.println("插入数据：------>"+userName_value+"已存在！");
			}
		}
	}
	
	/** 更新数据
	 * @MethodName: update
	 * @Description: 
	 * @param db
	 * @param collectionName
	 */
	private void update(DB db, String collectionName){
		DBCollection dbCollect = getDBCollection(db, collectionName);
		if(null != dbCollect){
			DBObject searchObj = new BasicDBObject("user_id", "1");
			DBObject updateOb = new BasicDBObject().append("$set", 
					new BasicDBObject().append("email", "wangshuai@eversec.com.cn")
					.append("post_code", "100086")
					.append("address", "北京市海淀区牡丹园"));
			WriteResult result = dbCollect.update(searchObj, updateOb, false, true);
System.out.println("更新数据：------>"+(result.getN() > 0 ? "成功" : "失败")+"！");
		}
	}
	
	private void findAll(DB db, String collectionName){
		DBCollection dbCollect = getDBCollection(db, collectionName);
		if(null != dbCollect){
			DBCursor dbCursor = dbCollect.find();
			while(dbCursor.hasNext()){
				DBObject dbObject = dbCursor.next();
System.out.println("用户名称：--------->"+dbObject.get("user_name"));
			}
		}
	}
	
	/** 删除数据
	 * @MethodName: delete
	 * @Description: 
	 * @param db
	 * @param collectionName
	 */
	private void delete(DB db, String collectionName){
		DBCollection dbCollect = getDBCollection(db, collectionName);
		if(null != dbCollect){
			DBObject delObj = new BasicDBObject("user_id", "1");
			WriteResult result = dbCollect.remove(delObj);
System.out.println("删除数据：------>"+(result.getN() == 0 ? "成功" : "失败")+"！");
		}
	}
	
	/** 删除集合
	 * @MethodName: dropCollection
	 * @Description: 
	 * @param db
	 * @param collectionName
	 */
	private void dropCollection(DB db, String collectionName){
		DBCollection dbCollect = getDBCollection(db, collectionName);
		if(null != dbCollect){
			dbCollect.drop();
		}
	}
}