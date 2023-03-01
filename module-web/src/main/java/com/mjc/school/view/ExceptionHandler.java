package com.mjc.school.view;

import com.mjc.school.service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ExceptionHandler {
		@Around("execution(* com.mjc.school.view.command.CommandExecutor.*(..))")
		public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
				try {
						joinPoint.proceed();
				} catch (CustomException e) {
						System.out.println(e.getMessage());
				}
				return  null;
		}
}

