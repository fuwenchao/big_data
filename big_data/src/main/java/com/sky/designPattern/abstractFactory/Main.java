package com.sky.designPattern.abstractFactory;

import com.sky.designPattern.abstractFactory.provider.Provider;
import com.sky.designPattern.abstractFactory.provider.impl.SmsSenderFactory;
import com.sky.designPattern.abstractFactory.sender.Sender;

/** 测试抽象工厂模式
 * @ClassName: Main
 * @Description: 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
 * @author Sky
 * @date 2016年7月13日 下午4:57:01
 * @version V1.0
 */
public class Main {
	
	public static void main(String[] args){
		Provider provider = new SmsSenderFactory();
		Sender sender = provider.produce();
		sender.send();
	}
}