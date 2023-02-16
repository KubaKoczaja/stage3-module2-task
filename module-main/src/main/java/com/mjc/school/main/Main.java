package com.mjc.school.main;

import com.mjc.school.repository.DataGenerator;
import com.mjc.school.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
		public static void main(String[] args) {
				DataGenerator.generateData();
				ApplicationContext context = new AnnotationConfigApplicationContext("com.mjc.school");
				View view = context.getBean("view", View.class);
				view.start();
		}
}
