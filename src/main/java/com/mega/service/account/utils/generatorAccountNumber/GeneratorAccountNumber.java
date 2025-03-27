package com.mega.service.account.utils.generatorAccountNumber;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class GeneratorAccountNumber {
    private static final SecureRandom random = new SecureRandom();

    @Bean
    public String generateAccountNumber() {
        long number = 1000000000L + (Math.abs(random.nextLong()) % 9000000000L);
        return "ACC-" + number;
    }
}
