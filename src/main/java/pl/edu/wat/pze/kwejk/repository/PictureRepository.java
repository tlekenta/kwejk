package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.pze.kwejk.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
