package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.NewsModelDto;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.ValidateNewsContent;
import com.mjc.school.service.validator.ValidateNewsId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsModelServiceImpl implements BaseService<NewsModel, NewsModelDto, Long> {
		private final BaseRepository<NewsModel, Long> newsModelRepository;
		private final NewsMapper newsMapper;

		@Override
		public List<NewsModelDto> readAll() {
				return newsModelRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}

		@Override
		@ValidateNewsId
		public NewsModelDto readById(Long id) {
				return newsMapper
								.newsToNewsDTO(newsModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No news with such id!")));
		}

		@Override
		@ValidateNewsContent
		public NewsModelDto create(NewsModel createRequest) {
				NewsModel savedNews = newsModelRepository.create(createRequest);
				return newsMapper.newsToNewsDTO(savedNews);
		}

		@Override
		@ValidateNewsContent
		public NewsModelDto update(NewsModel updateRequest) {
				NewsModel updatedNews = newsModelRepository.update(updateRequest);
				return newsMapper.newsToNewsDTO(updatedNews);
		}

		@Override
		@ValidateNewsId
		public boolean deleteById(Long id) {
				return newsModelRepository.deleteById(id);
		}
}