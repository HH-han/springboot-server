package com.example.travel.utils;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class EmailUtils {
    private static final String CODE_POOL = "0123456789";
    private static final int CODE_LENGTH = 6;

    public String generateCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CODE_POOL.charAt(random.nextInt(CODE_POOL.length())));
        }
        return sb.toString();
    }
}