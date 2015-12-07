package org.kosta.finalproject.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoggingAspect {
	private Log log=LogFactory.getLog(getClass());
	@Before("execution(public * org.kosta..*Service.find*(..))")
	public void beforeLogging(JoinPoint point){
		log.info("find 계열 메서드에 aop 적용");
	}
}








