package me.weekbelt.club.controller;

import lombok.extern.slf4j.Slf4j;
import me.weekbelt.club.sercurity.dto.ClubAuthMemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/sample")
@Controller
public class SampleController {

    @GetMapping("/all")
    public void exAll() {
        log.info("exAll...............");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {

        log.info("exMember.............");

        log.info("---------------------------");
        log.info("{}", clubAuthMemberDTO);
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin.............");
    }
}
