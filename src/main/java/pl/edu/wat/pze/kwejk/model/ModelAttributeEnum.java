package pl.edu.wat.pze.kwejk.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ModelAttributeEnum {
    /*COMMON*/
    ACTIVE_VIEW("activeView"),
    ACTIVE_ENDPOINT("activeEndpoint"),

    /*PAGINATION*/
    ACTUAL_PAGE_NUMBER("actualPageNumber"),
    LAST_PAGE_NUMBER("lastPageNumber"),
    PAGES_NUMBERS_LIST("pagesNumbersList"),

    /*GALLERY*/
    PICTURES_LIST("picturesList"),

    /*VIEW(ARTICLE=PICTURE)*/
    CURRENT_PICTURE("actualPicture"),

    /*PROFILE*/
    HAS_PICTURES("hasPictures"),
    ;

    private final String attributeName;

    @Override
    public String toString() {
        return attributeName;
    }

}
