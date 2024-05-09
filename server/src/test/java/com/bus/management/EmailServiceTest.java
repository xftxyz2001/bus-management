package com.bus.management;

import com.bus.management.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmailWithNullArguments() {
        // EmailService emailService = new EmailService(); // 假设已配置好
        assertThrows(IllegalArgumentException.class, () -> {
            emailService.sendEmail(null, "Test Subject", "Test message");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            emailService.sendEmail("test@example.com", null, "Test message");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            emailService.sendEmail("test@example.com", "Test Subject", null);
        });
    }
}
