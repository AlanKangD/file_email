package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;//이것을 통해서 메일을 보내겠다라는 빈입니다.
	
	
	public void sendMail(String to, String subject, String body) {
		
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = 
					new MimeMessageHelper(message, true, "utf-8");
			helper.setTo(to); //아이디
			helper.setSubject(subject); //제목
			helper.setText(body, true); //내용
			//여기에서 true로 하게 되면 jsp 형식으로 보내주면 jsp형식으로 받아줍니다.
			
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   public void auth(HttpServletRequest request) {
		      HttpSession session = request.getSession();
		      String userid = "kno12350@gmail.com";
		      String userkey = rand();
		      session.setAttribute(userid, userkey);
		      String body="<h2>안녕하세요 아뱅입니다</h2><hr>"
		            +"<h3>"+userid+" 님</h3>"
		            +"<p>인증하기 버튼을 누르면 로그인 됩니다</p>"
		            +"<a href='http://localhost:8085"
		            +request.getContextPath()+"/auth_check?userid="
		            +userid+"&userkey="+userkey+"'>인증하기</a>";
		      sendMail("qkqtodn1@naver.com","이메일 인증입니다",body);
		   }
		   private String rand() { //랜덤 문자를 만드는 코드입니다.
		      Random ran = new Random();
		      String str="";
		      int num;
		      while(str.length() != 20) {
		         num = ran.nextInt(75)+48;//0~74 + 48 (숫자,소문자, 대문자)만 뽑겠다
		         if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
		        	 //조건식에는 아스키표의 특수문자를 포함시키지 않도록 조건을 넣어준것
		            str+=(char)num;
		         }else {
		            continue;
		         }
		      }
		      return str;
		   }


}
