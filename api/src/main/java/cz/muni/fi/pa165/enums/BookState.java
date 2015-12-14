package cz.muni.fi.pa165.enums;

import cz.muni.fi.pa165.constants.BookStateConstants;

/**
 * Enum representig possible state/condition which a book can have
 * 
 * @author Michael Simacek
 *
 */
public enum BookState {
    /**
     * No damage
     * */
    NEW(BookStateConstants.NEW),
    /**
     * Lightly damaged
     * */
    LIGHT_DAMAGE(BookStateConstants.LIGHT_DAMAGE),
    /**
     * Medium level of damage
     * */
    MEDIUM_DAMAGE(BookStateConstants.MEDIUM_DAMAGE),
    /**
     * Heavily damaged
     * */
    HEAVY_DAMAGE(BookStateConstants.HEAVY_DAMAGE),
    /**
     * Removed from database, possibly missing
     * */
    REMOVED(BookStateConstants.REMOVED);

    private final String value;

    BookState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
