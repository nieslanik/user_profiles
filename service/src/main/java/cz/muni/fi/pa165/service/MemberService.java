package cz.muni.fi.pa165.service;

import java.util.List;
import java.util.Set;

import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;

/**
 * Service layer for Member entity
 *
 * Created by Juraj Tomko on 11/23/2015.
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
     * Removes member from database
     *
     * @param member entity to be removed
     */
    void deleteMember(Member member);


    /**
     * Returns all loans of member
     *
     * @param member
     * @return list of all loans possessed by member or null
     */
    List<Loan> getAllLoans(Member member);

    /**
     * Authenticates member if password matches the records
     *
     * @param member to authenticateMember
     * @param unhashedPassword hashed password to be matched
     * @return boolean
     */
    boolean authenticateMember(Member member, String unhashedPassword);

    /**
     * Registers member
     * @param member to registerMember
     * @param unhashedPassword
     */
    void registerMember(Member member, String unhashedPassword);

    /**
     * Checks if member is admin
     * @param member to check
     * @return boolean
     */
    boolean isAdmin(Member member);
}
