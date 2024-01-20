package freevoice.core.auth.registration.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("your-smtp-host");
        mailSender.setPort(587);
        mailSender.setUsername("your-username");
        mailSender.setPassword("your-password");

        // Additional properties, if needed
        // mailSender.setJavaMailProperties(javaMailProperties());

        return mailSender;
    }

    // If you have additional properties, you can include them here
    // private Properties javaMailProperties() {
    //     Properties properties = new Properties();
    //     properties.setProperty("mail.smtp.auth", "true");
    //     properties.setProperty("mail.smtp.starttls.enable", "true");
    //     return properties;
    // }
}
