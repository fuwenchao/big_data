package com.sky.designPattern.builder;

import com.sky.designPattern.builder.sender.Sender;

/** 测试建造者模式
 * @ClassName: Main
 * @Description: 建造者模式将很多功能集成到一个类里，这个类可以创造出比较复杂的东西。所以与工程模式的区别就是：工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象，多个部分。因此，是选择工厂模式还是建造者模式，依实际情况而定。
 * @author Sky
 * @date 2016年7月13日 下午6:56:38
 * @version V1.0
 */
public class Main {
	
	public static void main(String[] args) {
		Builder builder = new Builder();
		builder.addEmailSender(5);
		for(Sender sender : builder.getSenderList()){
			sender.send();
		}
	}
}