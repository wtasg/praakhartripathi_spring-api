package com.url_shortner.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UrlCodeGenerator {
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private final Random random;

    public UrlCodeGenerator() {
        this.random = new Random();
    }

    public String generate() {
        String code = "";

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARSET.length());
            code = code + CHARSET.charAt(index);
        }
        return code;
    }
}
