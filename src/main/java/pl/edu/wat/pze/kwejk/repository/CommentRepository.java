package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.pze.kwejk.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
