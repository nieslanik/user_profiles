package cz.muni.fi.pa165.enums;

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
    NEW("new"),
    /**
     * Lightly damaged
     * */
    LIGHT_DAMAGE("light_damage"),
    /**
     * Medium level of damage
     * */
    MEDIUM_DAMAGE("medium_damage"),
    /**
     * Heavily damaged
     * */
    HEAVY_DAMAGE("heavy_damage"),
    /**
     * Removed from database, possibly missing
     * */
    REMOVED("removed");

    private final String value;

    BookState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
