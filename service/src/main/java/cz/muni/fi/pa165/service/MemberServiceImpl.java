package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MemberDao;
import cz.muni.fi.pa165.entity.Loan;
import cz.muni.fi.pa165.entity.Member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

/**
 * Created by Juraj on 11/23/2015.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Inject
    MemberDao memberDao;

    @Override
    public Member findById(Long id) {
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Override
    public void delete(Member member) {
        memberDao.delete(member);
    }

    @Override
    public void addLoanToMember(Member member, Loan loan) {
        member.addLoan(loan);
        memberDao.update(member);
    }

    @Override
    public Set<Loan> getAllLoansOfMember(Member member) {
        Long id = member.getId();
        return memberDao.findById(id).getLoans();
    }

    @Override
    public boolean authenticate(Member member, String unhashedPassword) {
        return member.getPasswordHash().equals(makeSha1Hash(unhashedPassword));
    }

    @Override
    public boolean isAdmin(Member member) {
        return member.isAdmin();
    }

    @Override
    public void register(Member member, String password) {
        member.setPasswordHash(makeSha1Hash(password));
        memberDao.create(member);
    }

    public String makeSha1Hash(String input)
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA1");
            md.reset();
            byte[] buffer = input.getBytes();
            md.update(buffer);
            byte[] digest = md.digest();

            String hexString = "";
            for (int i = 0; i < digest.length; i++)
            {
                hexString += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
            }
            return hexString;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
