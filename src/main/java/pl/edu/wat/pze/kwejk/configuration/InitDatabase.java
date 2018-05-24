package pl.edu.wat.pze.kwejk.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class InitDatabase {

    PictureRepository pictureRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void initDb() {
        Picture[] pPictures = createPictures();
        pictureRepository.saveAll(Arrays.asList(pPictures));

    }

    private Picture[] createPictures() {

        return new Picture[]{
                new Picture((long) 1, "1.jpg", "Obrazek pierwszy", "Opis obrazka pierwszego", 1,  "oo jaki dlugi" +
                        " artykul, noniemoge"),
                new Picture((long) 2, "plakat.png", "Obrazek drugi", "Opis obrazka drugiego", 2, null),
                new Picture((long) 3, "spd1.jpg", "Obrazek trzeci", "Opis obrazka trzeciego", 3,  ""),
                new Picture((long) 4, "testo.jpg", "Obrazek czwarty", "Opis obrazka czwartego", 4,  "artykul o " +
                        "zyciu"),
                new Picture((long) 5, "testo2.jpg", "Obrazek piąty", "Opis obrazka piątego", 42,
                        "putinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputinputin ")
        };
    }

}