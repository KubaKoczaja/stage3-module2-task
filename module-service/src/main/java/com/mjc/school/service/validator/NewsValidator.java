package com.mjc.school.service.validator;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.exception.InvalidContentException;
import com.mjc.school.service.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@RequiredArgsConstructor
public class NewsValidator {
		private final DataSource dataSource;
		@Before("@annotation(ValidateNewsId)")
		public void validateIfEntityIdExists(JoinPoint joinPoint) {
				Long id = (Long) joinPoint.getArgs()[0];
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				if (newsModelList.stream().map(NewsModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException("No such id!");
				}
		}
		@Before("@annotation(ValidateNewsContent)")
		public void validateNewsContent(JoinPoint joinPoint) {
				NewsModel newsModel = (NewsModel) joinPoint.getArgs()[0];
				if (newsModel.getTitle().length() < 5 || newsModel.getTitle().length() > 30) {
						throw new InvalidContentException("Title must be between 5 and 30 characters long!");
				}
				if (newsModel.getContent().length() < 5 || newsModel.getContent().length() > 255) {
						throw new InvalidContentException("Title must be between 5 and 255 characters long!");
				}
		}
}
