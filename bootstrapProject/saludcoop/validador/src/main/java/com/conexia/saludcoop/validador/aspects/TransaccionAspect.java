package com.conexia.saludcoop.validador.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransaccionAspect {

	private static Logger LOGGER = LoggerFactory.getLogger(TransaccionAspect.class);

	@Around("execution(* com.conexia.saludcoop.validador.controller.*.*(..))")
	public Object grabarTrx(ProceedingJoinPoint call) throws Throwable {

		Object resp = null;
		try {
			long inicio = System.currentTimeMillis();
			LOGGER.info("Voy a ejecutar transaccion con datos: ");
			for (Object arg : call.getArgs()) {
				LOGGER.info(arg.toString());
			}
			resp = call.proceed();
			long fin = System.currentTimeMillis();
			LOGGER.info("Tiempo ejecucion: " + (fin - inicio) + " milisegundos");

		} catch (Throwable ex) {
			LOGGER.error(ex.toString());
			throw ex;
		}
		return resp;
	}
}
