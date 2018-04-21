package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;

import java.util.LinkedList;
import java.util.List;

@Service
public class PictureService {

    public List<Picture> getPicturesForPage(int aPageNumber){
        List<Picture> numbersList = new LinkedList<>();

        for(int i = 1; i <= 10; i++){
            numbersList.add(new Picture((aPageNumber-1)*10+i));
        }

        return numbersList;
    }

}
