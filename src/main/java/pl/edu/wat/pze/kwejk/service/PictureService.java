package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class PictureService {

    PictureRepository pictureRepository;

    public int getMaxPage() {
        return (int) Math.ceil( 1.0 * pictureRepository.count() / PaginationService.NUMBER_OF_PICS_ON_PAGE);
    }

    public List<Picture> getPicturesForPage(int aPageNumber) {
        return pictureRepository.findAll(PageRequest.of(aPageNumber - 1, PaginationService.NUMBER_OF_PICS_ON_PAGE)).getContent();
    }

}
