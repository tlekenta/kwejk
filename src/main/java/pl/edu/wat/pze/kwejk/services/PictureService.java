package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.MockService;
import pl.edu.wat.pze.kwejk.model.Picture;

import java.util.Arrays;
import java.util.List;


@Service
public class PictureService {

    private MockService mockService;

    public PictureService(MockService mockService) {
        this.mockService = mockService;
    }

    public List<Picture> getPicturesForPage(int aPageNumber){
        return Arrays.asList(mockService.getMOCK_PICTURES());
    }

}
