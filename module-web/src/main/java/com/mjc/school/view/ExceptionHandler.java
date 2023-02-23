package com.mjc.school.view;

import com.mjc.school.service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ExceptionHandler {
		private final View view;
		@AfterThrowing(pointcut = "@annotation(CommandHandler)", throwing = "e")
		public void handleException(CustomException e) {
				System.out.println(e.getMessage());
				view.start();
		}
}
