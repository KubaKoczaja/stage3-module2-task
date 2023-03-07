package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsModelController;
import com.mjc.school.controller.command.annotation.CommandHandler;
import com.mjc.school.service.NewsModelService;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsModelControllerImpl implements NewsModelController {
		private final NewsModelService newsModelService;
		@Override
		@CommandHandler
		public List<NewsModelDto> readAll() {
				return newsModelService.readAll();
		}

		@Override
		@CommandHandler
		public NewsModelDto readById(Long id) {
				return newsModelService.readById(id);
		}

		@Override
		@CommandHandler
		public NewsModelDto create(NewsModelDto createRequest) {
				return newsModelService.create(createRequest);
		}

		@Override
		@CommandHandler
		public NewsModelDto update(NewsModelDto updateRequest) {
				NewsModelDto newsReadById = newsModelService.readById(updateRequest.getId());
				updateRequest.setAuthorId(newsReadById.getAuthorId());
				return newsModelService.update(updateRequest);
		}

		@Override
		@CommandHandler
		public boolean deleteById(Long id) {
				return newsModelService.deleteById(id);
		}
}