package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.dto.AuthorModelDto;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validator.ValidateAuthorId;
import com.mjc.school.service.validator.ValidateAuthorsName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorModelServiceImpl implements BaseService<AuthorModel, AuthorModelDto, Long> {
		private final BaseRepository<AuthorModel, Long> authorModelRepository;
		private final BaseRepository<NewsModel, Long> newsModelRepository;
		private final AuthorMapper authorMapper;
		@Override
		public List<AuthorModelDto> readAll() {
				return authorModelRepository.readAll().stream().map(authorMapper::authorToAuthorDto).toList();
		}

		@Override
		@ValidateAuthorId
		public AuthorModelDto readById(Long id) {
				return authorMapper
								.authorToAuthorDto(authorModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No author with such id!")));
		}

		@Override
		@ValidateAuthorsName
		public AuthorModelDto create(AuthorModel createRequest) {
				AuthorModel savedAuthor = authorModelRepository.create(createRequest);
				return authorMapper.authorToAuthorDto(savedAuthor);
		}

		@Override
		@ValidateAuthorsName
		public AuthorModelDto update(AuthorModel updateRequest) {
				AuthorModel updatedAuthor = authorModelRepository.update(updateRequest);
				return authorMapper.authorToAuthorDto(updatedAuthor);
		}

		@Override
		@ValidateAuthorId
		public boolean deleteById(Long id) {
				List<NewsModel> newsWithConcreteAuthor = newsModelRepository.readAll()
								.stream()
								.filter(n -> n.getAuthorId().equals(id))
								.toList();
				if (!newsWithConcreteAuthor.isEmpty()) {
						newsWithConcreteAuthor.forEach(n -> newsModelRepository.deleteById(n.getId()));
				}
				return authorModelRepository.deleteById(id);
		}
}
