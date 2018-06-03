package pl.edu.wat.pze.kwejk.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.pze.kwejk.model.PicValidEnum;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


@Component
public class PictureValidator {

    private int MAX_SIZE = 10485760; //10Mb
    private int MAX_HEIGHT = 1200;
    private int MAX_WIDTH = 890;
    private PicValidEnum result = PicValidEnum.OK;

    public PicValidEnum validateImageFile(MultipartFile file) {

        result=PicValidEnum.OK;
        if (checkType(file) && checkSize(file)) {
            checkResolution(file);
        } else System.out.println("Wrong type or file size");

        return result;
    }

    public boolean checkType(MultipartFile file) {
        if (!file.getContentType().startsWith("image")) {
            result = PicValidEnum.INCORRECT_FILE_TYPE;
            return false;
        }
        return true;
    }

    public boolean checkSize(MultipartFile file) {
        if (file.getSize() > MAX_SIZE) {
            result = PicValidEnum.INCORRECT_WEIGHT;
            return false;
        }
        return true;
    }

    public void checkResolution(MultipartFile file) {
        File tmp = new File(String.valueOf(file.hashCode()));
        try {
            Files.write(tmp.toPath(), file.getBytes(), StandardOpenOption.CREATE_NEW);
            BufferedImage bimg = ImageIO.read(tmp);
            if (bimg.getHeight() > MAX_HEIGHT || bimg.getWidth() > MAX_WIDTH) {
                System.out.println("Wrong resolution");
                result = PicValidEnum.INCORRECT_RESOLUTION;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            result = PicValidEnum.IOError;
        } finally {
            deleteTmpFile(tmp);
        }

    }

    public void deleteTmpFile(File tmp) {
        if (!tmp.delete()) System.err.println("Delete tmp file failed");
    }
}
