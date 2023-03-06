package com.mjc.school.view.command;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.NewsModel;
import com.mjc.school.repository.dto.AuthorModelDto;
import com.mjc.school.repository.dto.NewsModelDto;
import com.mjc.school.view.command.annotation.CommandBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProvider {
		private final BaseController<NewsModel, NewsModelDto, Long> newsModelControllerImpl;
		private final BaseController<AuthorModel, AuthorModelDto, Long> authorModelControllerImpl;

		@CommandBody(id = 1)
		public Command readAllNews() {
				return () -> newsModelControllerImpl.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 2)
		public Command readAllAuthors() {
				return () -> authorModelControllerImpl.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 3)
		public Command readNewsById(Long id) {
				return () -> System.out.println(newsModelControllerImpl.readById(id));
		}
		@CommandBody(id = 4)
		public Command readAuthorById(Long id) {
				return () -> System.out.println(authorModelControllerImpl.readById(id));
		}
		@CommandBody(id = 5)
		public Command createNews(NewsModel newsModel) {
				return () -> newsModelControllerImpl.create(newsModel);
		}
		@CommandBody(id = 6)
		public Command createAuthor(AuthorModel authorModel) {
				return () -> authorModelControllerImpl.create(authorModel);
		}
		@CommandBody(id = 7)
		public Command updateNews(NewsModel newsModel) {
				return () -> newsModelControllerImpl.update(newsModel);
		}
		@CommandBody(id = 8)
		public Command updateAuthor(AuthorModel authorModel) {
				return () -> authorModelControllerImpl.update(authorModel);
		}
		@CommandBody(id = 9)
		public Command deleteNewsById(Long id) {
				return () -> newsModelControllerImpl.deleteById(id);
		}
		@CommandBody(id = 10)
		public Command deleteAuthorById(Long id) {
				return () -> authorModelControllerImpl.deleteById(id);
		}
		@CommandBody(id = 11)
		public Command exit() {
				return () -> System.out.println("Exit");
		}
		@CommandBody(id = 12)
		public Command invalidOption() {
				return () -> System.out.println("Invalid option number!");
		}
}
