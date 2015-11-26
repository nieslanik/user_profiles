package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LoanDTO;
import cz.muni.fi.pa165.dto.MemberAuthenticateDTO;
import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.dto.MemberRegisterDTO;
import cz.muni.fi.pa165.entity.Member;
import cz.muni.fi.pa165.service.MemberService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Juraj on 11/26/2015.
 */
@Service
@Transactional
public class MemberFacadeImpl implements MemberFacade {

    @Inject
    Mapper mapper;

    @Inject
    MemberService service;

    @Override
    public MemberDTO findById(Long id) {
        return mapper.map(service.findById(id), MemberDTO.class);
    }

    @Override
    public List<MemberDTO> findAll() {
        return service.findAll()
                .stream()
                .map(x -> mapper.map(x, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMember(Long id) {
        service.deleteMember(service.findById(id));
    }

    @Override
    public List<LoanDTO> getAllLoans(Long id) {
        Member member = service.findById(id);
        return service.getAllLoans(member)
                .stream()
                .map(x -> mapper.map(x, LoanDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean authenticateMember(MemberAuthenticateDTO memberAuth) {
        return service.authenticateMember(service.findById(memberAuth.getMemberId()), memberAuth.getPassword());
    }

    @Override
    public void registerMember(MemberRegisterDTO memberReg) {
        service.registerMember(service.findById(memberReg.getMember().getId()), memberReg.getPassword());
    }

    @Override
    public boolean isAdmin(Long id) {
        return service.findById(id).isAdmin();
    }
}
