package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.dto.AuthorModelDto;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validator.ValidateAuthorId;
import com.mjc.school.service.validator.ValidateAuthorsDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorModelServiceImpl implements BaseService<AuthorModel, AuthorModelDto, Long> {
		private final BaseRepository<AuthorModel, Long> authorModelRepository;
		private final AuthorMapper authorMapper;
		@Override
		public List<AuthorModelDto> readAll() {
				return authorModelRepository.readAll().stream().map(authorMapper::authorToAuthorDto).toList();
		}

		@Override
		@ValidateAuthorId
		public AuthorModelDto readById(Long id) {
				return authorMapper.authorToAuthorDto(authorModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No author with such id!")));
		}

		@Override
		@ValidateAuthorsDetails
		public AuthorModelDto create(AuthorModel createRequest) {
				AuthorModel savedAuthor = authorModelRepository.create(createRequest);
				return authorMapper.authorToAuthorDto(savedAuthor);
		}

		@Override
		@ValidateAuthorsDetails
		public AuthorModelDto update(AuthorModel updateRequest) {
				AuthorModel updatedAuthor = authorModelRepository.update(updateRequest);
				return authorMapper.authorToAuthorDto(updatedAuthor);
		}

		@Override
		@ValidateAuthorId
		@OnDelete
		public boolean deleteById(Long id) {
				return authorModelRepository.deleteById(id);
		}
}
