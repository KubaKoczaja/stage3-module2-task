package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class AuthorModel implements BaseEntity<Long>{
		private Long id;
		private String name;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
}