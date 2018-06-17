package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModelAttributeEnum {
    /*COMMON*/
    ACTIVE_VIEW("activeView"),
    ACTIVE_ENDPOINT("activeEndpoint"),
    HAS_PICTURES("hasPictures"),

    /*PAGINATION*/
    ACTUAL_PAGE_NUMBER("actualPageNumber"),
    LAST_PAGE_NUMBER("lastPageNumber"),
    PAGES_NUMBERS_LIST("pagesNumbersList"),

    /*GALLERY*/
    PICTURES_LIST("picturesList"),

    /*VIEW(ARTICLE=PICTURE)*/
    CURRENT_PICTURE("actualPicture"),

    /*SEARCH*/
    KEY_WORD("keyWord"),
    NO_RESULT("noResult")
    ;

    private final String attributeName;

    @Override
    public String toString() {
        return attributeName;
    }

}
