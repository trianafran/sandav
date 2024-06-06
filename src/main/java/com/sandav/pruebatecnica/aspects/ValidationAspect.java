package com.sandav.pruebatecnica.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
	private static Logger logger = LogManager.getLogger(ValidationAspect.class);
	
	@Around("execution(* com.sandav.pruebatecnica.service.SpaceshipService.findById(..))")
	public void tiempoPasado(ProceedingJoinPoint point) throws Throwable {	    
	    Long id = (Long)point.getArgs()[0];	    
	    if(id<0) {
	    	logger.warn(String.format("Method: %s. Id must not be negative.", point.getSignature().toString()));
	    }
	}
	
//	@Around("execution(* com.arquitecturajava.servicios.*.*())")
//	public void tiempoPasado(ProceedingJoinPoint punto) throws Throwable {
//	 
//	Long tiempo1 = System.currentTimeMillis();
//	punto.proceed();
//	Long tiempo2 = System.currentTimeMillis();
//	Long total = tiempo2 - tiempo1;
//	if (total == 2000)
//	System.out.format("el metodo es : %s y el tiempo transcurrido %d\n", punto.getSignature().getName(), total);
//	 
//	}

}