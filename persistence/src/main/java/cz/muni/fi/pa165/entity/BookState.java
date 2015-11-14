package cz.muni.fi.pa165.entity;

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
    NEW,
    /**
     * Lightly damaged
     * */
    LIGHT_DAMAGE,
    /**
     * Medium level of damage
     * */
    MEDIUM_DAMAGE,
    /**
     * Heavily damaged
     * */
    HEAVY_DAMAGE,
    /**
     * Removed from database, possibly missing
     * */
    REMOVED
}
