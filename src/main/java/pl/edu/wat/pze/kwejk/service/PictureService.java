package pl.edu.wat.pze.kwejk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.repository.PaginationRepository;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PictureService {

    private final
    PictureRepository pictureRepository;
    private final
    PaginationRepository paginationRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository, PaginationRepository paginationRepository) {
        this.pictureRepository = pictureRepository;
        this.paginationRepository = paginationRepository;
    }

    public int getMaxPage() {
        return (int) Math.ceil(1.0 * pictureRepository.count() / PaginationService.NUMBER_OF_PICS_ON_PAGE);
    }

    //sorted by Date
    public List<Picture> getPicturesForPage(int aPageNumber) {
        return paginationRepository.findAll(PageRequest.of(aPageNumber - 1, PaginationService
                .NUMBER_OF_PICS_ON_PAGE)).getContent();
    }

    public Picture getPictureById(Long id) {
        return pictureRepository.getOne(id);
    }

    public List<Picture> getPictures() {
        return pictureRepository.findAll().stream()
                .filter(p -> p.getArticle().equals("") || p.getArticle() == null)
                .collect(Collectors.toList());
    }

    public void save(Picture picture) {
        pictureRepository.save(picture);
        System.out.println("picture::: " + picture);
    }


}