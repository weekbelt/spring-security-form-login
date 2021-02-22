package me.weekbelt.club.sercurity.service;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.weekbelt.club.entity.ClubMember;
import me.weekbelt.club.repository.ClubMemberRepository;
import me.weekbelt.club.sercurity.dto.ClubAuthMemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubUserDetailService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailService loadUserByUsername " + username);

        final Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        final ClubMember clubMember = result.get();

        log.info("--------------");
        log.info("{}", clubMember);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
            clubMember.getEmail(),
            clubMember.getPassword(),
            clubMember.isFromSocial(),
            clubMember.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet()));

        clubAuthMember.setName(clubAuthMember.getName());
        clubAuthMember.setFromSocial(clubAuthMember.isFromSocial());

        return clubAuthMember;
    }
}
