package com.example.PandaCoffee.service;

import com.example.PandaCoffee.dto.request.EmailRequest;
import com.example.PandaCoffee.dto.request.SendEmailRequest;
import com.example.PandaCoffee.dto.response.EmailResponse;
import com.example.PandaCoffee.dto.response.Sender;
import com.example.PandaCoffee.dto.response.To;
import com.example.PandaCoffee.repositories.CodeConfirmEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.PandaCoffee.repositories.EmailSend;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    EmailSend emailSend;
    @Autowired


    @Value("${subject.interview}")
    private String subjectInterview;
    @Value("${subject.htmlContent}")
    private String subjectHtmlContent;

    @Autowired
    CodeConfirmEmailRepository codeConfirmEmailRepository;
    String apiKey = "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";

    public EmailResponse sendEmail(SendEmailRequest sendEmailRequest){
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .email("hn9875388@gmail.com")
                        .name("MinhHoang")
                        .build())
                .to(List.of(sendEmailRequest.getTo()))
                .subject(sendEmailRequest.getSubject())
                .htmlContent(sendEmailRequest.getHtmlContent())
                .build();

        return emailSend.email(apiKey,emailRequest);
    }

    public EmailResponse sendEmailWhenAccountRegister(To to, String message){
        String subject = "Xác Thực Tài Khoản";
        String link = "http://localhost:8080/api/confirmEmail?message=" + message;
//        String htmlContent = "<p>Xin chào!!</p><p>Cảm ơn bạn đã đăng ký tài khoản. Vui lòng nhấn vào liên kết dưới đây để xác thực tài khoản của bạn:</p> " + "<a href=\"\{link}\">Xác Thực Tài Khoản</a>";
        String htmlContent = String.format(
                "<p>Xin chào!!</p><p>Cảm ơn bạn đã đăng ký tài khoản. Vui lòng nhấn vào liên kết dưới đây để xác thực tài khoản của bạn:</p>" +
                        "<a href=\"%s\">Xác Thực Tài Khoản</a>",
                link
        );
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .email("hn9875388@gmail.com")
                        .name("MinhHoang")
                        .build())
                .to(List.of(to))
                .subject(subject)
                .htmlContent(htmlContent)
                .build();

        return emailSend.email(apiKey,emailRequest);
    }


    //Email khi nha tuyen dung chinh sua cuoc phong van







}
