package com.sky.designPattern.abstractFactory.provider.impl;

import com.sky.designPattern.abstractFactory.provider.Provider;
import com.sky.designPattern.abstractFactory.sender.Sender;
import com.sky.designPattern.abstractFactory.sender.impl.SmsSender;

public class SmsSenderFactory implements Provider {
	
	@Override
	public Sender produce() {
		return new SmsSender();
	}
}