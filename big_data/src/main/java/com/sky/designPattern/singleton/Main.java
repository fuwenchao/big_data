package com.sky.designPattern.singleton;

/** 测试单例模式
 * @ClassName: Main
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午4:59:01
 * @version V1.0
 */
public class Main {
	
	public static void main(String[] args){
		Singleton singleton = Singleton.getInstance();
		singleton.say();
	}
}