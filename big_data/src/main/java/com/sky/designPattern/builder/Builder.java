package com.sky.designPattern.builder;

import java.util.ArrayList;
import java.util.List;

import com.sky.designPattern.builder.sender.Sender;
import com.sky.designPattern.builder.sender.impl.EmailSender;
import com.sky.designPattern.builder.sender.impl.SmsSender;

/** 建造者类
 * @ClassName: Builder
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午7:09:58
 * @version V1.0
 */
public class Builder {
	
	private List<Sender> senderList = new ArrayList<Sender>();
	
	public List<Sender> getSenderList() {
		return senderList;
	}
	
	/** 添加邮件发送
	 * @MethodName: addEmailSender
	 * @Description: 
	 * @param num
	 */
	public void addEmailSender(int num){
		for(int i=0; i<num; i++){
			senderList.add(new EmailSender());
		}
	}
	
	/** 添加短信发送
	 * @MethodName: 
	 * @Description: 
	 * @param num
	 */
	public void addSmsSender(int num){
		for(int i=0; i<num; i++){
			senderList.add(new SmsSender());
		}
	}
}