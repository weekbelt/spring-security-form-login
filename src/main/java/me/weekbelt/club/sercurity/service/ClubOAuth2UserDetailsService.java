package me.weekbelt.club.sercurity.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.weekbelt.club.entity.ClubMember;
import me.weekbelt.club.entity.ClubMemberRole;
import me.weekbelt.club.repository.ClubMemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("-----------------------------");
        log.info("userRequest: " + userRequest);

        final String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info("{}", userRequest.getAdditionalParameters());

        final OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("=============================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);
        });

        String email = null;
        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }

        ClubMember clubMember = saveSocialMember(email);

        return oAuth2User;
    }

    private ClubMember saveSocialMember(String email) {
        final Optional<ClubMember> result = repository.findByEmail(email, true);
        if (result.isPresent()) {
            return result.get();
        }

        final ClubMember clubMember = ClubMember.builder()
            .name(email)
            .password(passwordEncoder.encode("1111"))
            .fromSocial(true)
            .build();

        clubMember.addMemberRole(ClubMemberRole.USER);
        repository.save(clubMember);

        return clubMember;
    }
}
