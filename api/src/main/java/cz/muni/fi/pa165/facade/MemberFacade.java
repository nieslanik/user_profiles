package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

/**
 * Created by Juraj Tomko on 11/23/2015.
 */

public interface MemberFacade {

    /**
     * Creates new member
     * @param member data transfer object
     */
    void createMember(MemberDTO member);

    /**
     * Finds member by id
     * @param id
     * @return member dto or null
     */
    MemberDTO findById(Long id);

    /**
     * Returns all members
     * @return list of members
     */
    List<MemberDTO> findAll();

    /**
     * Removes member
     * @param id
     */
    void deleteMember(Long id);

    /**
     * Adds new loan to member
     * @param newLoan dto
     */
    void addNewLoan(NewLoanDTO newLoan);

    /**
     * Removes loan from current loans
     * @param id
     */
    void removeFromCurrentLoans(Long id);

    /**
     * Returns all current loans possessed by member
     * @param id
     * @return list of loans
     */
    List<LoanDTO> getCurrentLoans(Long id);

    /**
     * Returns all loans made by member
     * @param id
     * @return list of loans
     */
    List<LoanDTO> getAllLoans(Long id);

    /**
     * Makes authentization of member
     * @param memberAuth
     * @return true if authorization passed, else false
     */
    boolean authenticateMember(MemberAuthenticateDTO memberAuth);

    /**
     * Registers new member
     * @param memberReg
     * @return true if registration was successful, else false
     */
    boolean registerMember(MemberRegisterDTO memberReg);

    /**
     * Checks if member has admin permissions
     * @param id
     * @return boolean
     */
    boolean isAdmin(Long id);
}
