package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public interface PaginationRepository extends PagingAndSortingRepository<Picture, Long> {

    Page<Picture> findAllByOrderByDateDesc(Pageable pageable);
    Page<Picture> findAllByUserOrderByDateDesc(Pageable pageable, User user);
    Page<Picture> findAllByDateAfterOrderByPointsDesc(Pageable pageable, Date date);
    Page<Picture> findByTitleContaining(Pageable pageable, String title);
}
