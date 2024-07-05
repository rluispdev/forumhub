package rluispdev.forum_hub.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TopicListDTO(
        @NotBlank String title,
        @NotBlank String message,
        @JsonAlias("creation_date") LocalDateTime creationDate,
        Status status,
        Long id_user,
        Theme theme ){

    public TopicListDTO(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getStatus(), topic.getId_user(), topic.getTheme());

    }
}
