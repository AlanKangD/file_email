package com.care.root.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public static JavaMailSender mailSender() {
   JavaMailSenderImpl jms = new JavaMailSenderImpl();
   jms.setHost("smtp.gmail.com"); // 메일 서버 설정
   jms.setPort(587);  //구글의 포트번호이고 고정이다
   jms.setUsername("kno12350@gmail.com");// 내 계정 아이디
   jms.setPassword("!Dehddnjs4140");	//내 계정의 비밀번호

   Properties prop = new Properties();
   prop.setProperty("mail.transport.protocol", "smtp");
   prop.setProperty("mail.smtp.auth", "true"); 
   prop.setProperty("mail.smtp.starttls.enable", "true");
   
   prop.setProperty("mail.debug", "true"); // 디버그 로그를 찍겠다는 의미
   
   jms.setJavaMailProperties(prop);

   return jms;
    } //사용자의 설정을 하는 페이지 입니다.
}