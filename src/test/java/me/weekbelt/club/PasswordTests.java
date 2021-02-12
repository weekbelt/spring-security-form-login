package me.weekbelt.club;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("패스워드인코더 테스트")
    public void testcase() {
        String password = "1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println("enPw: " + enPw);
        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult: " + matchResult);
    }

}
