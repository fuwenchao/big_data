package com.sky.designPattern.builder.sender.impl;

import com.sky.designPattern.builder.sender.Sender;

/** 发送邮件实现类
 * @ClassName: EmailSender
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午3:48:18
 * @version V1.0
 */
public class EmailSender implements Sender {
	
	@Override
	public void send() {
		System.out.println("发送Email...");
	}
}