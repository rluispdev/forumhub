package rluispdev.forum_hub.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topic")
    private Long id;

    private String title;
    private String message;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "id_user")
    private Long id_user;

    @Column(name = "theme")
    @Enumerated(EnumType.STRING)
    private Theme theme;

    public Topic(TopicDTO data) {
        this.title = data.title();
        this.message = data.message();
        this.creationDate = data.creationDate();
        this.status = data.status();
        this.id_user = data.id_user();
        this.theme = data.theme();


    }
    // Lifecycle callback to set creationDate before persistence
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
}
