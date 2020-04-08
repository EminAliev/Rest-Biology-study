package ru.itis.biology.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private String text;
    private Long newsId;
}
