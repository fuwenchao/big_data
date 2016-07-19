package com.sky.designPattern.abstractFactory.provider;

import com.sky.designPattern.abstractFactory.sender.Sender;

/** 提供者
 * @ClassName: Provider
 * @Description: 
 * @author Sky
 * @date 2016年7月13日 下午7:10:25
 * @version V1.0
 */
public interface Provider {
	
	public Sender produce(); 
}