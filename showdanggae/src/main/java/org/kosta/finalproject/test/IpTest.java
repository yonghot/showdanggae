package org.kosta.finalproject.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 현재 서버의 IP 주소를 가져옵니다.
 * 
 * 	Enumeration 인터페이스는 객체들의 집합(Vector)에서 각각의 객체들을 
 * 한순간에 하나씩 처리할 수 있는 메소드를 제공하는 켈렉션이다.
 * 인터페이스이므로, 직접 new 연산자를 이용하여 객체를 생성할 수 없으며, 
 * Enumeration 인터페이스에 선언된 메소드는 그 인터페이스를 사용하는 클래스로 구현해서 사용해야만 한다.
 * 
 * - boolean hasMoreElements() : Vector로 부터 생성된 Enumeration의 요소가 있으면 true, 아니면 false 반환
 * - Object nextElement() : Enumeration 내의 다음 요소를 반환한다. 
 * 
 * Enumeration 객체는 new 연산자로 생성할 수 없으며, Vector를 이용하여 생성할 수 있다.
 * Vector 클래스의 elements() 라는 메소드는 객체의 모든 요소들을 Enumeration 객체로 반환한다.
 * 
 * @return IP 주소
 */
public class IpTest {
	private String getLocalServerIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return null;
	}
}
	
	

