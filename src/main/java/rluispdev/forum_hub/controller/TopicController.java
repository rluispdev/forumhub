package rluispdev.forum_hub.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rluispdev.forum_hub.topic.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository repository;

   //CRIAR_TOPICO
    @PostMapping
    public ResponseEntity<?> toPublish(@RequestBody @Valid TopicDTO data) {
        System.out.println(data);
        repository.save(new Topic(data));
        return ResponseEntity.status(201).body("Topic created successfully");
    }

    //LISTAR_TOPICOS_OPEN_E_CLOSED
    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable){
        List<Status> statusList = List.of(Status.OPEN, Status.CLOSED); // Lista os status desejados
        var page = repository.findAllByStatusIn(statusList, pageable).map(TopicListDTO::new);
        return ResponseEntity.ok(page);
    }

    //DETALHAR_TOPICO
    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
        var topic =repository.getReferenceById(id);
        return ResponseEntity.ok(new TopicDetailsDTO(topic));
    }

    //ATUALIZAR_TOPICO
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateTopicDTO data){
        var topic =repository.getReferenceById(data.id());
        topic.updateInfo(data);
        return ResponseEntity.ok(new UpdateTopicDTO(topic));
    }

    //DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Topic> optionalTopic = repository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.delete(); // This will set active to false
            repository.save(topic); // Save the updated topic
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
