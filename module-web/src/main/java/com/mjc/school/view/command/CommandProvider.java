package com.mjc.school.view.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.AuthorModelDto;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidOptionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProvider {
		private final BaseController<NewsModel, NewsModelDto, Long> newsModelController;
		private final BaseController<AuthorModel, AuthorModelDto, Long> authorModelController;

		@CommandBody(id = 1)
		public Command readAllNews() {
				return () -> newsModelController.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 2)
		public Command readAllAuthors() {
				return () -> authorModelController.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 3)
		public Command readNewsById(Long id) {
				return () -> System.out.println(newsModelController.readById(id));
		}
		@CommandBody(id = 4)
		public Command readAuthorById(Long id) {
				return () -> System.out.println(authorModelController.readById(id));
		}
		@CommandBody(id = 5)
		public Command createNews(NewsModel newsModel) {
				return () -> newsModelController.create(newsModel);
		}
		@CommandBody(id = 6)
		public Command createAuthor(AuthorModel authorModel) {
				return () -> authorModelController.create(authorModel);
		}
		@CommandBody(id = 7)
		public Command updateNews(NewsModel newsModel) {
				return () -> newsModelController.update(newsModel);
		}
		@CommandBody(id = 8)
		public Command updateAuthor(AuthorModel authorModel) {
				return () -> authorModelController.update(authorModel);
		}
		@CommandBody(id = 9)
		public Command deleteNewsById(Long id) {
				return () -> newsModelController.deleteById(id);
		}
		@CommandBody(id = 10)
		public Command deleteAuthorById(Long id) {
				return () -> authorModelController.deleteById(id);
		}
		@CommandBody(id = 11)
		public Command exit() {
				return () -> System.out.println("Exit");
		}
		@CommandBody(id = 12)
		public Command invalidOption() {
				throw  new InvalidOptionException("Invalid option!");
		}

}
