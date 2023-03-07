package com.mjc.school.controller.implementation;

import com.mjc.school.controller.AuthorModelController;
import com.mjc.school.controller.command.annotation.CommandHandler;
import com.mjc.school.service.AuthorModelService;
import com.mjc.school.service.dto.AuthorModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorModelControllerImpl implements AuthorModelController {
		private final AuthorModelService authorModelService;
		@Override
		@CommandHandler
		public List<AuthorModelDto> readAll() {
				return authorModelService.readAll();
		}

		@Override
		@CommandHandler
		public AuthorModelDto readById(Long id) {
				return authorModelService.readById(id);
		}

		@Override
		@CommandHandler
		public AuthorModelDto create(AuthorModelDto createRequest) {
				return authorModelService.create(createRequest);
		}

		@Override
		@CommandHandler
		public AuthorModelDto update(AuthorModelDto updateRequest) {
				AuthorModelDto authorReadById = authorModelService.readById(updateRequest.getId());
				updateRequest.setCreateDate(authorReadById.getCreateDate());
				return authorModelService.update(updateRequest);
		}

		@Override
		@CommandHandler
		public boolean deleteById(Long id) {
				return authorModelService.deleteById(id);
		}
}