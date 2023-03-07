package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.AuthorModelService;
import com.mjc.school.service.dto.AuthorModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorModelControllerImpl implements BaseController<AuthorModelDto, AuthorModelDto, Long> {
		private final AuthorModelService authorModelService;
		@Override
		public List<AuthorModelDto> readAll() {
				return authorModelService.readAll();
		}

		@Override
		public AuthorModelDto readById(Long id) {
				return authorModelService.readById(id);
		}

		@Override
		public AuthorModelDto create(AuthorModelDto createRequest) {
				return authorModelService.create(createRequest);
		}

		@Override
		public AuthorModelDto update(AuthorModelDto updateRequest) {
				AuthorModelDto authorReadById = authorModelService.readById(updateRequest.getId());
				updateRequest.setCreateDate(authorReadById.getCreateDate());
				return authorModelService.update(updateRequest);
		}

		@Override
		public boolean deleteById(Long id) {
				return authorModelService.deleteById(id);
		}
}