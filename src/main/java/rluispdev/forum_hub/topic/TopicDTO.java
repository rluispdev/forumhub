package rluispdev.forum_hub.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TopicDTO(
        @NotBlank String title,
        @NotBlank String message,
        @JsonAlias("creation_date") LocalDateTime creationDate,
        Status status,
        Long id_user,
        Theme theme
) {

}
