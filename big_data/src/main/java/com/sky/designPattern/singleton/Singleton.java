package com.sky.designPattern.singleton;

/** 单例模式
 * @ClassName: Singleton
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午5:20:28
 * @version V1.0
 */
public class Singleton {
	
	// 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载。
	private static Singleton singleton = null;
	
	/** 私有构造方法，防止被实例化
	 * @MethodName: Singleton
	 * @Description: 
	 */
	private Singleton(){}
	
	/** 创建实例
	 * @MethodName: getInstance
	 * @Description: 
	 * @return 
	 */
	public static Singleton getInstance(){
		if(null == singleton){
			syncInit();
		}
		return singleton;
	}
	
	private static synchronized void syncInit(){
		if(null == singleton){
			singleton = new Singleton();
		}
	}
	
	public void say(){
		System.out.println("你好，单例模式!");
	}
}