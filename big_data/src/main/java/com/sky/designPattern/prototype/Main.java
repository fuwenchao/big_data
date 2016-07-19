package com.sky.designPattern.prototype;

import java.io.IOException;

/** 测试原型模式
 * @ClassName: Main
 * @Description: 原型模式虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。
 * @author Sky
 * @date 2016年7月13日 下午7:17:55
 * @version V1.0
 */
public class Main {
	
	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Prototype prototype = new Prototype();
		// 浅复制
		prototype.clone();
		// 深复制
		prototype.deepClone();
	}
}