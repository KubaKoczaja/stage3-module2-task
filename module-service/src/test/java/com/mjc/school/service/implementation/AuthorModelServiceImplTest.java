package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorModelRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.dto.AuthorModelDto;
import com.mjc.school.service.mapper.AuthorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorModelServiceImplTest {
		@InjectMocks
		private AuthorModelServiceImpl authorModelService;
		@Mock
		private AuthorModelRepositoryImpl authorModelRepository;
		@Mock
		private AuthorMapper authorMapper;

		@Test
		void shouldReadAllAuthors() {
				List<AuthorModel> authorModelList = List.of(new AuthorModel(), new AuthorModel());
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(new AuthorModelDto());
				when(authorModelRepository.readAll()).thenReturn(authorModelList);
				int lengthExpected = 2;
				assertEquals(lengthExpected, authorModelService.readAll().size());
		}

		@Test
		void shouldReturnEntityWithGivenId() {
				AuthorModelDto expected = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorModelRepository.readById(anyLong())).thenReturn(Optional.of(new AuthorModel()));
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(expected);
				assertEquals(expected, authorModelService.readById(1L));
		}

		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				AuthorModel authorModelToCreate = new AuthorModel(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorModelRepository.create(any(AuthorModel.class))).thenReturn(new AuthorModel());
				AuthorModelDto savedAuthorDto = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(savedAuthorDto);
				assertEquals(savedAuthorDto, authorModelService.create(authorModelToCreate));
		}

		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				AuthorModel authorModelToUpdate = new AuthorModel(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				AuthorModelDto updatedAuthorDto = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorModelRepository.update(any(AuthorModel.class))).thenReturn(authorModelToUpdate);
				lenient().when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(updatedAuthorDto);
				assertEquals(updatedAuthorDto, authorModelService.update(authorModelToUpdate));
		}

		@Test
		void shouldReturnTrueWhenEntityIsDeleted() {
				when(authorModelRepository.deleteById(anyLong())).thenReturn(true);
				assertTrue(authorModelService.deleteById(1L));
		}
}