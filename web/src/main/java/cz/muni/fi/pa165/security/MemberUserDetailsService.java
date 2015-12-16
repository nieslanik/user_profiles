package cz.muni.fi.pa165.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.dto.MemberDTO;
import cz.muni.fi.pa165.facade.MemberFacade;

@Component
public class MemberUserDetailsService implements UserDetailsService {
    @Inject
    MemberFacade facade;

    @Override
    // our username is email
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDTO member = facade.findByEmail(email);
        if (member == null)
            throw new UsernameNotFoundException(email);
        return new MemberUserDetailsAdapter(member);
    }
}
