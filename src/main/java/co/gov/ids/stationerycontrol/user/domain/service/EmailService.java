package co.gov.ids.stationerycontrol.user.domain.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import co.gov.ids.stationerycontrol.user.domain.dto.Email;
import org.springframework.mail.javamail.MimeMessageHelper;
import co.gov.ids.stationerycontrol.user.domain.repository.IEmailRepository;

@Service
public class EmailService implements IEmailRepository {

    private final JavaMailSender sender;

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public boolean sendEmail(Email emailBody) {
        return sendEmailTool(emailBody.getContent(), emailBody.getTo(), emailBody.getSubject());
    }

    private boolean sendEmailTool(String textMessage, String email, String subject) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            send = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return send;
    }

}
