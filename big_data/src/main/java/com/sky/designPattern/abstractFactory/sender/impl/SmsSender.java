package com.sky.designPattern.abstractFactory.sender.impl;

import com.sky.designPattern.abstractFactory.sender.Sender;

/** 发送短信实现类
 * @ClassName: SmsSender
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午3:48:31
 * @version V1.0
 */
public class SmsSender implements Sender {
	
	@Override
	public void send() {
		System.out.println("发送Sms...");
	}
}