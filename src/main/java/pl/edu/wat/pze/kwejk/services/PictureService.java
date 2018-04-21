package pl.edu.wat.pze.kwejk.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.model.Picture;

import java.util.LinkedList;
import java.util.List;

@Service
public class PictureService {

    private static String[] AVAILABLE_IMAGES = new String[] {"1.jpg", "plakat.png", "spd1.jpg", "testo.jpg", "testo2.jpg"};

    public List<Picture> getPicturesForPage(int aPageNumber){
        List<Picture> numbersList = new LinkedList<>();

        for(int i = 1; i <= 10; i++){
            numbersList.add(new Picture(AVAILABLE_IMAGES[i%AVAILABLE_IMAGES.length], "Tytuł obrazka", "Opis obrazka"));
        }

        return numbersList;
    }

}
