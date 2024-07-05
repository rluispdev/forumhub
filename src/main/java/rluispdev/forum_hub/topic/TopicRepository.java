package rluispdev.forum_hub.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByStatusIn(List<Status> status, Pageable pageable);
    @Query("SELECT t FROM Topic t WHERE t.active = true")
    List<Topic> findAllActive();
}