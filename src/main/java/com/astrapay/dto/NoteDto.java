package com.astrapay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;

@Data
@Builder
public class NoteDto {

    private Long id;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    private String description;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
