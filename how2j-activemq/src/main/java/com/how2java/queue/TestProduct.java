package com.how2java.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.how2java.util.ActiveMQUtil;

public class TestProduct {
	private static String url = "tcp://127.0.0.1:61616";
	
	public static void main(String[] args) throws JMSException {
		ActiveMQUtil.checkServer();
		
		//创建ConnectionFactory,绑定地址
		ConnectionFactory cf = new ActiveMQConnectionFactory(url);
		//创建connection
		Connection con = cf.createConnection();
		//启动连接
		con.start();
	}
}
