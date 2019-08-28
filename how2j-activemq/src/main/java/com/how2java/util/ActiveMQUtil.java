package com.how2java.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.util.NetUtil;


public class ActiveMQUtil {

	private static Logger log = LoggerFactory.getLogger(ActiveMQUtil.class);
	
	public static void checkServer() {
		 if(NetUtil.isUsableLocalPort(8161)) {
	            //JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
			 	log.error("ActiveMQ 服务器未启动");
	            System.exit(1);
	        }
	}
}
