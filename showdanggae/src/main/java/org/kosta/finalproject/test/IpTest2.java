package org.kosta.finalproject.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpTest2 {
	private String IpTest(){
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = local.getHostAddress();
		System.out.println(ip);
		return ip;
	}
	
}
