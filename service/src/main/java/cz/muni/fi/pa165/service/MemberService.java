package cz.muni.fi.pa165.service;

import java.util.List;

import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;

/**
 * Service layer for Member entity
 *
 * @author Juraj Tomko
 */
public interface MemberService {

    /**
     * Returns member with given id or null
     *
     * @param id of requested member
     * @return member or null
     */
    Member findById(Long id);

    /**
     * Returns all persisted members
     *
     * @return list of all members
     */
    List<Member> findAll();

    /**
     * Finds members by name
     *
     * @param email of member
     * @return list of members
     */
    Member findByEmail(String email);

    /**
     * Finds members by name
     *
     * @param name of member
     * @return list of members
     */
    List<Member> findByName(String name);

    /**
     * Removes member from database
     *
     * @param member entity to be removed
     */
    void deleteMember(Member member);

    /**
     * Returns all loans of member
     *
     * @param member member to be queried
     * @return list of all loans possessed by member or null
     */
    List<Loan> getAllLoans(Member member);

    /**
     * Authenticates member if password matches the records
     *
     * @param member to authenticateMember
     * @param unhashedPassword hashed password to be matched
     * @return whether authentication was succesful
     */
    boolean authenticateMember(Member member, String unhashedPassword);

    /**
     * Registers member
     *
     * @param member to registerMember
     * @param unhashedPassword password string
     */
    void registerMember(Member member, String unhashedPassword);

    /**
     * Checks if member is admin
     *
     * @param member to check
     * @return whether member is an admin
     */
    boolean isAdmin(Member member);

    /**
     * Makes user admin
     * @param member to make admin
     */
    void makeAdmin(Member member);
}
