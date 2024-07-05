package rluispdev.forum_hub.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UpdateTopicDTO(Long id,
                               String title,
                               String message
                            ) {

    public UpdateTopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(),topic.getMessage());
    }
}
