package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.pze.kwejk.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
