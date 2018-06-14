package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wat.pze.kwejk.model.Picture;

import java.util.Date;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    int countAllByDateAfter(Date date);
}
