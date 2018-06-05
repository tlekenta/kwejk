package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote getVoteByUserAndPicture(User user, Picture picture);

}
