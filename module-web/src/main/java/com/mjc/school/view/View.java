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
						switch (menuOption) {
								case 1 -> allNewsView();
								case 2 -> newsByIdView();
								case 3 -> createNewsView();
								case 4 -> updateNewsView();
								case 5 -> deleteByIdView();
								case 0 -> exitView();
								default -> {
										System.out.println("Please try again.");
										start();
								}
						}
				} while(menuOption != 0);
		}

		private void allNewsView() {
				System.out.println("List of all news");
				newsModelController.readAll().forEach(System.out::println);
		}

		private void newsByIdView() {
				System.out.println("Please enter news id:");
				try {
						System.out.println(newsModelController.readById(scanner.nextLong()));
				} catch (NoSuchEntityException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		private void createNewsView() {
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
				try {
						newsModelController.create(newsModel);
				} catch (InvalidContentException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		private void deleteByIdView() {
				scanner.nextLine();
				System.out.println("Please enter news to remove:");
				try {
						newsModelController.deleteById(scanner.nextLong());
				} catch (NoSuchEntityException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		private void updateNewsView() {
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
				try {
						newsModelController.update(newsModelToUpdate);
				} catch (NoSuchEntityException | InvalidContentException e) {
						System.out.println(e.getMessage());
						start();
				}
		}
		private void exitView() {
				System.out.println("Exit");
		}
}