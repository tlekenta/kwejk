package pl.edu.wat.pze.kwejk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.wat.pze.kwejk.model.Picture;

public interface PaginationRepository extends PagingAndSortingRepository<Picture, Long> {
    @Override
    @Query("SELECT p FROM Picture p ORDER BY p.date desc")
    Page<Picture> findAll(Pageable pageable);
}
