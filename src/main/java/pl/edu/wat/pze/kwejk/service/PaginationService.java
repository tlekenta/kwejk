package pl.edu.wat.pze.kwejk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class PaginationService {

    private static final int MAX_NUMBER_OF_PAGES_TO_DISPLAY = 7;
    public static final int NUMBER_OF_PICS_ON_PAGE = 4;
    private static int MAX_PAGE;

    private PictureService pictureService;

    @Autowired
    public PaginationService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    /**
     * List of all pages
     * @param aActivePage
     * @return
     */
    public List<Integer> getPagesList(Integer aActivePage) {
        return doSearch(aActivePage, pictureService.getMaxPage());
    }

    /**
     * List of pages on profile with @username
     * @param aActivePage
     * @param userName
     * @return
     */
    public List<Integer> getPagesList(Integer aActivePage, String userName) {
        return doSearch(aActivePage, pictureService.getMaxPage(userName));
    }

    private List<Integer> doSearch(Integer aActivePage, int MAX_PAGE) {
        if (aActivePage <= Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY) / 2)
            return getFirstPages(aActivePage, MAX_PAGE);
        else if (aActivePage > MAX_PAGE - Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY) / 2)
            return getLastPages(aActivePage, MAX_PAGE);
        else
            return getMiddlePages(aActivePage, MAX_PAGE);
    }

    private List<Integer> getFirstPages(Integer aActivePage, final int MAX_PAGE) {
        return getPagesFromRange(1, Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY), aActivePage);
    }

    private List<Integer> getLastPages(Integer aActivePage, final int MAX_PAGE) {
        return getPagesFromRange(MAX_PAGE - Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY) + 1,
                MAX_PAGE, aActivePage);
    }

    private List<Integer> getMiddlePages(Integer aActivePage, final int MAX_PAGE) {
        return getPagesFromRange(aActivePage - Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY) / 2,
                aActivePage + Math.min(MAX_PAGE, MAX_NUMBER_OF_PAGES_TO_DISPLAY) / 2, aActivePage);
    }

    private List<Integer> getPagesFromRange(Integer aFrom, Integer aTo, Integer aActivePage) {
        List<Integer> pPagesList = new LinkedList<>();

        if (aActivePage > aTo || aActivePage < aFrom)
            throw new IllegalStateException(MessageFormat.format("Active page is not in selected range! Range is <{0}, {1}> and active page is {2}", aFrom, aTo, aActivePage));

        for (int i = aFrom; i <= aTo; i++) {
            pPagesList.add(i);
        }

        return pPagesList;
    }

}
