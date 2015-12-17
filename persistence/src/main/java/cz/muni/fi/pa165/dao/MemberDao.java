package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Member;

/**
 * Data access object for Member entity.
 *
 * @author Jakub Peschel
 */
public interface MemberDao {

    /**
     * Creates persistent representation of Member in the database. Sets the ID.
     *
     * @param member Member object to be persisted
     *
     */
    void create(Member member);

    /**
     * Updates entity data in database
     *
     * @param member Member object to be updated
     *
     */
    void update(Member member);

    /**
     * Finds a member in the database by its ID
     *
     * @param id member id
     * @return Found Member object or null if no loan was found.
     *
     */
    Member findById(Long id);

    /**
     * Retrieves all Member from the database
     *
     * @return List of all Members
     *
     */
    List<Member> findAll();

    /**
     * Finds members by name
     *
     * @param name of member
     * @return list of members
     */
    List<Member> findByName(String name);

    /**
     * Finds members by name
     *
     * @param email of member
     * @return list of members
     */
    List<Member> findByEmail(String email);

    /**
     * Removes given Member from the database
     *
     * @param member member to be deleted
     *
     */
    void delete(Member member);   
}
