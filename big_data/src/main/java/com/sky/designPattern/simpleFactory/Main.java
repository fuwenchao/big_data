package com.sky.designPattern.simpleFactory;

import com.sky.designPattern.simpleFactory.sender.Sender;

/** 测试简单工厂模式
 * @ClassName: Main
 * @Description: 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 * @author Sky
 * @date 2016年7月13日 下午4:58:09
 * @version V1.0
 */
public class Main {
	
	public static void main(String[] args){
		Sender sender = SenderFactory.getInstance("email");
		if(null != sender){
			sender.send();
		}else {
			System.out.println("请输入正确的类型!");
		}
	}
}