package com.sky.designPattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 原型模式
 * @ClassName: Prototype
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午7:17:31
 * @version V1.0
 */
public class Prototype implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 浅复制
	 * @MethodName: clone
	 * @Description: 将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
	 * @return Object
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/** 深复制
	 * @MethodName: deepClone
	 * @Description: 将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
	 * 				   深复制需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
	 * @return Object
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Object deepClone() throws IOException, ClassNotFoundException {
		// 写入当前对象的二进制流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		// 读出二进制流产生的新对象
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}