package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.NewsModel;
import com.mjc.school.repository.dto.NewsModelDto;
import com.mjc.school.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsModelControllerImpl implements BaseController<NewsModel, NewsModelDto, Long> {
		private final BaseService<NewsModel, NewsModelDto, Long> newsModelService;
		@Override
		public List<NewsModelDto> readAll() {
				return newsModelService.readAll();
		}

		@Override
		public NewsModelDto readById(Long id) {
				return newsModelService.readById(id);
		}

		@Override
		public NewsModelDto create(NewsModel createRequest) {
				return newsModelService.create(createRequest);
		}

		@Override
		public NewsModelDto update(NewsModel updateRequest) {
				return newsModelService.update(updateRequest);
		}

		@Override
		public boolean deleteById(Long id) {
				return newsModelService.deleteById(id);
		}
}