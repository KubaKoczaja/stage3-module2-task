package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.dto.AuthorModelDto;
import com.mjc.school.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorModelControllerImpl implements BaseController<AuthorModel, AuthorModelDto, Long> {
		private final BaseService<AuthorModel, AuthorModelDto, Long> authorModelService;
		@Override
		public List<AuthorModelDto> readAll() {
				return authorModelService.readAll();
		}

		@Override
		public AuthorModelDto readById(Long id) {
				return authorModelService.readById(id);
		}

		@Override
		public AuthorModelDto create(AuthorModel createRequest) {
				return authorModelService.create(createRequest);
		}

		@Override
		public AuthorModelDto update(AuthorModel updateRequest) {
				return authorModelService.update(updateRequest);
		}

		@Override
		public boolean deleteById(Long id) {
				return authorModelService.deleteById(id);
		}
}