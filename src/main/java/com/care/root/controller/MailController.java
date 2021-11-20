package com.care.root.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse response) throws IOException {
		
		StringBuffer sb = new StringBuffer(); //연산을 할 때 처리속도가 빠릅니다.
		sb.append("<h1>제품 소개</h1>");
		sb.append("<a href=\"https://github.com/AlanKangD\">");
		sb.append("<img src=\"https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDExMjlfMzAw%2FMDAxNjA2NTkwNTI1NTAw.FnEhMhLvc-NnjC6KVbWeC-f7z84jucd5nhfPMIdhbukg.DeHfW2gyjw1w0Vz7pvlhmliXzQC5cNcZMq9gaQP0J1kg.JPEG.yeskim83%2FFB_IMG_1606590388033.jpg&type=a340\">");
		sb.append("</a>");
		
		String str = sb.toString(); //String 은 처리속도가 느리다
		ms.sendMail("kno12350@gmail.com", "광고", str);
		//ms.sendMail("kno12350@gmail.com","(제목)연습 메일", "(내용)연습중");		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("메일을 전송 하였습니다.");
		
	}	
	@GetMapping("auth_form")
	public String authForm() {
		
		return "auth";
	}
	@GetMapping("auth")
	public String auth(HttpServletRequest request) {
		ms.auth(request);
		return "redirect:https://www.naver.com/"; //받고자 하는 주소
	}
	@GetMapping("auth_check")
	public String authCheck(@RequestParam String userid,
								@RequestParam String userkey,
									HttpSession session) {
		String sessionKey = (String)session.getAttribute(userid);
		if(sessionKey != null) {
			if(sessionKey.equals(userkey)) {
				session.setAttribute("userid", userid);
			}
			
		}
		return "redirect:auth_form";
	}
}
