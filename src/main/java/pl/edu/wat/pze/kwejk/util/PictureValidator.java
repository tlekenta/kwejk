package pl.edu.wat.pze.kwejk.util;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.pze.kwejk.model.PicValidEnum;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//import com.google.common.io.Files;

public class PictureValidator {

    private int MAX_SIZE = 10485760; //10Mb
    private int MAX_HEIGHT = 1200;
    private int MAX_WIDTH = 890;

    public PicValidEnum validateImage(MultipartFile image)  {
        if (!image.getContentType().startsWith("image"))
            return PicValidEnum.INCORRECT_FILE_TYPE;          // .jpg .png ..

        File tmp = new File("tmp");
//        Files.write(image.getBytes(), tmp); // nie widzi importu guava - do zrobienia potem, ide spac
        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(tmp);
        } catch (IOException e) {
            System.err.println("ImageIO.read error.\n" + e.getMessage());
        }
        if (image.getSize() > MAX_SIZE)
            return PicValidEnum.INCORRECT_WEIGHT;
        if (bimg.getHeight() > MAX_HEIGHT || bimg.getWidth() > MAX_WIDTH)
            return PicValidEnum.INCORRECT_RESOLUTION;
        return PicValidEnum.OK;
    }
}
