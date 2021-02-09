package me.weekbelt.club.controller;

import lombok.extern.slf4j.Slf4j;
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
    public void exMember() {
        log.info("exMember.............");
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin.............");
    }
}
