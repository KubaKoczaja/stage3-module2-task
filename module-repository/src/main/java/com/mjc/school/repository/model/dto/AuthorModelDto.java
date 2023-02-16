package com.mjc.school.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class AuthorModelDto implements BaseEntityDto<Long> {
		private Long id;
		private String name;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
}
