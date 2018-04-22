package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class PictureService {

    PictureRepository pictureRepository;

    public List<Picture> getPicturesForPage(int aPageNumber){
        return pictureRepository.findAll();
    }

}
