package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.MailBl;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Utils.Hash;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/mail")
public class MaiApi {
    @Autowired
    private MailBl mailBl;

    Logger logger = Logger.getLogger("bo.ucb.edu.environment.Api.MaiApi");

    @PostMapping("/send")
    public String sendMail(@RequestParam("email") String email) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(1025);
        mailSender.setUsername("");
        mailSender.setPassword("");
        //GEnerata a uuid
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // Generate a random 4-digit number

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");

        mailSender.setJavaMailProperties(properties);
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setFrom("universidad.catolica@ucb.edu.bo");
        helper.setTo(email);
        helper.setSubject("Recuperacion de contraseña");
        helper.setText("<h2>Codigo de recuperación</h2> Su código de recuperación es: \n" + randomNumber , true);

        mailSender.send(mimeMessage);
        mailBl.saveVerificationCode((long) randomNumber);
        return "Mail sent";
    }

    @PostMapping("/verify")
    public String verifyCode(@RequestParam("code") Long code, @RequestParam("email") String email, @RequestParam("password") String password) {
        logger.info("Verifying code");
        if (mailBl.verifyCode(code)) {
            mailBl.updatePassword(email, password);
            return "OK";
        } else {
            return "NOT OK";
        }
    }
}
