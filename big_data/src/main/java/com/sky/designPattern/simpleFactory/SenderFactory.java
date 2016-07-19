package com.sky.designPattern.simpleFactory;

import com.sky.designPattern.simpleFactory.sender.Sender;
import com.sky.designPattern.simpleFactory.sender.impl.EmailSender;
import com.sky.designPattern.simpleFactory.sender.impl.SmsSender;

/** 简单工厂类
 * @ClassName: SenderFactory
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午3:49:09
 * @version V1.0
 */
public class SenderFactory {
	
	public static Sender getInstance(String type){
		Sender sender = null;
		switch(type){
			case "email":
				sender = new EmailSender();
				break;
			case "sms":
				sender = new SmsSender();
				break;
			default:
				break;
		}
		return sender;
	}
}