package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsModelControllerImpl implements BaseController<NewsModelDto, NewsModelDto, Long> {
		private final NewsModelService newsModelService;
		@Override
		public List<NewsModelDto> readAll() {
				return newsModelService.readAll();
		}

		@Override
		public NewsModelDto readById(Long id) {
				return newsModelService.readById(id);
		}

		@Override
		public NewsModelDto create(NewsModelDto createRequest) {
				return newsModelService.create(createRequest);
		}

		@Override
		public NewsModelDto update(NewsModelDto updateRequest) {
				NewsModelDto newsReadById = newsModelService.readById(updateRequest.getId());
				updateRequest.setAuthorId(newsReadById.getAuthorId());
				return newsModelService.update(updateRequest);
		}

		@Override
		public boolean deleteById(Long id) {
				return newsModelService.deleteById(id);
		}
}