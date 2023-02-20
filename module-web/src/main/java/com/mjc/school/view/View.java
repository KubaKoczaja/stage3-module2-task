package com.mjc.school.view;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidContentException;
import com.mjc.school.service.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class View {
		private final Scanner scanner = new Scanner(System.in);
		private final DataSource dataSource;
		private final BaseController<NewsModel, NewsModelDto, Long> newsModelController;
		private final CommandExecutor commandExecutor;
		private int mainMenu() {
				System.out.println("""
						Enter the number of operation:
						1 - Get all news.
						2 - Get news by id.
						3 - Create news.
						4 - Update news.
						5 - Remove news by id.
						0 - Exit.""");
				return scanner.nextInt();
		}
		public void start() {
				int menuOption;
				do {
						menuOption = mainMenu();
						Command command = switch (menuOption) {
								case 1 -> allNewsView();
								case 2 -> newsByIdView();
								case 3 -> createNewsView();
								case 4 -> updateNewsView();
								case 5 -> deleteByIdView();
								case 0 -> exitView();
								default -> invalidOption();
						};
						commandExecutor.executeCommand(command);
				} while(menuOption != 0);
		}

		private Command allNewsView() {
				System.out.println("List of all news");
				return () -> newsModelController.readAll().forEach(System.out::println);
		}

		private Command newsByIdView() {
				System.out.println("Please enter news id:");
				Long id = scanner.nextLong();
				return () -> {
						try {
								System.out.println(newsModelController.readById(id));
						} catch (NoSuchEntityException e) {
								System.out.println(e.getMessage());
								start();
						}
				};
		}
		private Command createNewsView() {
				scanner.nextLine();
				System.out.println("Please enter title:");
				String title = scanner.nextLine();
				System.out.println("Please enter content:");
				String content = scanner.nextLine();
				System.out.println("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				NewsModel newsModel = new NewsModel();
				newsModel.setId((long) dataSource.parseNewsFromFile().size());
				newsModel.setTitle(title);
				newsModel.setContent(content);
				newsModel.setCreateDate(LocalDateTime.now());
				newsModel.setLastUpdateDate(LocalDateTime.now());
				newsModel.setAuthorId(authorId);
				return () -> {
						try {
								newsModelController.create(newsModel);
						} catch (InvalidContentException e) {
								System.out.println(e.getMessage());
								start();
						}
				};
		}
		private Command deleteByIdView() {
				scanner.nextLine();
				System.out.println("Please enter news to remove:");
				Long id = scanner.nextLong();
				return () -> {
						try {
								newsModelController.deleteById(scanner.nextLong());
						} catch (NoSuchEntityException e) {
								System.out.println(e.getMessage());
								start();
						}
				};
		}
		private Command updateNewsView() {
				scanner.nextLine();
				System.out.println("Please enter news to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new title:");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.println("Please enter new content:");
				String content = scanner.nextLine();
				NewsModel newsModelToUpdate = new NewsModel();
				newsModelToUpdate.setId(id);
				newsModelToUpdate.setTitle(title);
				newsModelToUpdate.setContent(content);
				newsModelToUpdate.setLastUpdateDate(LocalDateTime.now());
				return () -> {
						try {
								newsModelController.update(newsModelToUpdate);
						} catch (InvalidContentException e) {
								System.out.println(e.getMessage());
								start();
						}
				};
		}
		private Command exitView() {
				return () -> System.out.println("Exit");
		}
		private Command invalidOption() {
				return () -> {
						System.out.println("Please try again.");
						start();
				};
		}
}