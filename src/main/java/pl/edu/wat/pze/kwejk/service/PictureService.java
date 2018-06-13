package pl.edu.wat.pze.kwejk.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.wat.pze.kwejk.auth.UserPrincipal;
import pl.edu.wat.pze.kwejk.model.Comment;
import pl.edu.wat.pze.kwejk.model.Picture;
import pl.edu.wat.pze.kwejk.model.User;
import pl.edu.wat.pze.kwejk.repository.CommentRepository;
import pl.edu.wat.pze.kwejk.repository.PaginationRepository;
import pl.edu.wat.pze.kwejk.repository.PictureRepository;
import pl.edu.wat.pze.kwejk.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final PaginationRepository paginationRepository;
    private final CommentRepository commentRepository;
    private UserRepository userRepository;

    public int getMaxPage() {
        return (int) Math.ceil(1.0 * pictureRepository.count() / PaginationService.NUMBER_OF_PICS_ON_PAGE);
    }

    public int getMaxPage(String userName) {
        return (int) Math.ceil(1.0 *
                userRepository.findByUsername(userName).getPictures().size()
                / PaginationService.NUMBER_OF_PICS_ON_PAGE);
    }

    public List<Picture> getPicturesForPage(int aPageNumber) {
        return paginationRepository.findAllByOrderByDateDesc(PageRequest.of(aPageNumber - 1, PaginationService
                .NUMBER_OF_PICS_ON_PAGE)).getContent();
    }

    public List<Picture> getPicturesForPage(int aPageNumber, String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) System.err.println("User: " + userName + " not found");
        return user != null ? paginationRepository.findAllByUserOrderByDateDesc(PageRequest.of(aPageNumber - 1,
                PaginationService.NUMBER_OF_PICS_ON_PAGE), user).getContent() : new ArrayList<>();
    }

    public Picture getPictureById(Long id) {
        return pictureRepository.getOne(id);
    }

    public List<Picture> getPictures() {
        return pictureRepository.findAll().stream()
                .filter(p -> p.getArticle().equals("") || p.getArticle() == null)
                .collect(Collectors.toList());
    }

    //getTopYear

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