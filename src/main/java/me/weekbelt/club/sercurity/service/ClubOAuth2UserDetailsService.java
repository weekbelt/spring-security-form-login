package me.weekbelt.club.sercurity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

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
        return oAuth2User;
    }
}
