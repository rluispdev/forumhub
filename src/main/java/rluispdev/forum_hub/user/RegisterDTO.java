package rluispdev.forum_hub.user;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RegisterDTO (
        String username,
        @JsonAlias("full_name") String fullName,
        String email,
        String password) {
}
