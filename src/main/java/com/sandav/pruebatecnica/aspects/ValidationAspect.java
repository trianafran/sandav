package com.sandav.pruebatecnica.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.sandav.pruebatecnica.valueObject.Spaceship;

@Aspect
@Component
public class ValidationAspect {
	private static Logger logger = LogManager.getLogger(ValidationAspect.class);
	
	@Around("execution(* com.sandav.pruebatecnica.service.SpaceshipService.findById(..))")
	public Spaceship validateFindById(ProceedingJoinPoint point) throws Throwable {	    
	    Long id = (Long)point.getArgs()[0];	    
	    if(id<0) {
	    	logger.warn(String.format("Method: %s. Id must not be negative.", point.getSignature().toString()));
	    	throw new IllegalArgumentException("Id must not be negative");
	    } else 
	    	return (Spaceship)point.proceed();
	}
}