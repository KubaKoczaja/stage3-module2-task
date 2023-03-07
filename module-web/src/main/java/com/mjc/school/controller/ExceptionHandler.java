package com.mjc.school.controller;

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
		private final View view;
		@Around("@annotation(com.mjc.school.controller.command.annotation.CommandHandler)")
		public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
				try {
						return joinPoint.proceed();
				} catch (CustomException e) {
						System.out.println(e.getMessage());
				}
				return "Please try again";
		}
}

