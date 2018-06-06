package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Comment;
import pl.edu.wat.pze.kwejk.repository.CommentRepository;

@Service
@AllArgsConstructor
public class CommentService {

    public CommentRepository commentRepository;

    public Comment save(Comment aComment) {
        return commentRepository.save(aComment);
    }

}
