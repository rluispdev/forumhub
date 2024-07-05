package rluispdev.forum_hub.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rluispdev.forum_hub.topic.*;

import java.util.List;

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
}
