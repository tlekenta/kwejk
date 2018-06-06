package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.auth.UserPrincipal;
import pl.edu.wat.pze.kwejk.model.Comment;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.repository.CommentRepository;
import pl.edu.wat.pze.kwejk.repository.PaginationRepository;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final PaginationRepository paginationRepository;
    private final CommentRepository commentRepository;

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

    public void addCommentToPicture(Long aPictureId, String text) {
        Comment pComment = new Comment();
        pComment.setPostDate(LocalDateTime.now());
        pComment.setCommentText(text);
        pComment.setUser(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        pComment = commentRepository.save(pComment);

        Picture pPicture = getPictureById(aPictureId);
        pPicture.getComments().add(pComment);

        save(pPicture);
    }


}