package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.dto.AuthorModelDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AuthorMapper {
		AuthorModelDto authorToAuthorDto(AuthorModel authorModel);
		AuthorModel authorDtoToAuthor(AuthorModelDto authorModelDto);
}
