package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsModel implements BaseEntity<Long>{
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createDate;
		private LocalDateTime lastUpdateDate;
		private Long authorId;
}
