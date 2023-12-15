package pl.javaps.shop.common.mail;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

//@Configuration
public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email.sender", matchIfMissing = true ,havingValue = "emailSimpleService")
    public EmailSender emailSimpleSender(JavaMailSender javaMailSender){
        return new EmailSimpleService(javaMailSender);
    }

    @ConditionalOnProperty(name = "app.email.sender", havingValue = "fakeEmailService")
    @Bean
    public EmailSender fakeSimpleSender(){
        return new FakeEmailService();
    }
}
